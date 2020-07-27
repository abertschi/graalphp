import os
import subprocess
from datetime import datetime
import pandas as pd
import csv
from io import StringIO
import statistics
from os.path import join
from bench_db import DbStorage

GRAALPHP_HOME = os.environ.get('GRAALPHP_HOME')
if not GRAALPHP_HOME:
    print("[!] GRAALPHP_HOME not set to run graalphp", flush=True)
    exit(1)

GRAALPHP_BINARY = join(GRAALPHP_HOME, "graalphp")
if not os.path.exists(GRAALPHP_BINARY):
    print("[!] GRAALPHP_BINARY not found: " + GRAALPHP_BINARY, flush=True)
    exit(1)

GRAALPHP_NATIVE_BINARY = join(GRAALPHP_HOME, "graalphp-native/graalphp-native")
if not os.path.exists(GRAALPHP_NATIVE_BINARY):
    print("[!] GRAALPHP_NATIVE_BINARY not found: " + GRAALPHP_NATIVE_BINARY, flush=True)
    exit(1)

PHP_BINARY = 'php'

DIR = os.path.dirname(os.path.realpath(__file__))
MEASUREMENT_DIR = join(DIR, 'measurements')
os.makedirs(MEASUREMENT_DIR, exist_ok=True)


class BenchMeasurement():

    def __init__(self,
                 test_name,
                 prefix,
                 out_file='',
                 src_file='',
                 binary='',
                 command='',
                 timings=None):
        self.test_name = test_name
        self.out_file = out_file
        self.src_file = src_file
        self.binary = binary
        self.command = command
        self.prefix = prefix
        self.timings = timings


class Bench:

    def __init__(self):
        self.db = DbStorage()
        pass

    def verify_file(self, path):
        if not os.path.exists(path):
            print("[!] file does not exist: " + path, flush=True)
            exit(1)

    def verify_files(self, files):
        for f in files:
            self.verify_file(f)

    def run_single_test(self, bench_name, file_prefix, exec_name, exec_binary, exec_args, exec_src):
        name = os.path.basename(exec_src)
        log_file = join(MEASUREMENT_DIR, '{}-{}-{}.txt'.format(file_prefix, name, exec_name))
        src_file = join(MEASUREMENT_DIR, '{}-{}-{}-source.txt'.format(file_prefix, name, exec_name))
        self._save_file(exec_src, src_file)

        exec = '{} {} {} | tee {}'.format(exec_binary, exec_args, exec_src, log_file)
        print("[i] - running: " + exec, flush=True)
        subprocess.call(exec, shell=True)

        return BenchMeasurement(test_name=bench_name,
                                prefix=file_prefix,
                                command=exec,
                                out_file=self.read_file(log_file),
                                src_file=self.read_file(src_file),
                                binary=exec_name,
                                timings=[])

    def run_php(self, bench, prefix, src, args=''):
        return self.run_single_test(bench, prefix, 'php', PHP_BINARY, args, src)

    def run_graalphp(self, bench, prefix, src, args=''):
        return self.run_single_test(bench, prefix, 'graalphp', GRAALPHP_BINARY, args, src)

    def run_graalphp_native(self, bench, prefix, src, args=''):
        return self.run_single_test(bench, prefix, 'graalphp-native', GRAALPHP_NATIVE_BINARY, args, src)

    def _save_file(self, source, dest):
        file1 = open(source, "r")
        file2 = open(dest, "w")
        l = file1.readline()
        while l:
            file2.write(l)
            l = file1.readline()
        file1.close()
        file2.close()

    def get_bench_time(self):
        return datetime.now().isoformat()

    def get_test_prefix(self, name):
        return '{}-{}'.format(self.get_bench_time(), name)

    def read_file(self, path):
        file = open(path, "r")
        l = True
        buffer = ""
        while l:
            l = file.readline()
            buffer += l
        print(buffer)
        return buffer

    # parse ; seperated values into array of arrays
    def parse_values(self, path):
        pd.set_option('display.max_rows', None)
        file = open(path, "r")
        l = True
        buffer = ""
        while l:
            l = file.readline()
            if ";" in l:
                buffer += l
        res = pd.read_csv(StringIO(buffer),
                          sep=';',
                          header=None,
                          error_bad_lines=False,
                          quoting=csv.QUOTE_NONE)
        return res

    def extract_timings(self, lines, data_item: BenchMeasurement = None):
        return []

    def extrat_data(self, data: BenchMeasurement):
        file = data.out_file
        lines = self.parse_values(file)
        timings = self.extract_timings(lines, data)
        self.print_statistics(timings)
        data.timings = timings
        return data

    def extract_and_store_data(self, data: BenchMeasurement):
        self.extrat_data(data)
        self.store_measurment(data)

    def store_measurment(self, data: BenchMeasurement):
        self.db.store_measurements(test_name=data.test_name,
                                   prefix=data.prefix,
                                   timings=data.timings,
                                   src_file=data.src_file,
                                   out_file=data.out_file,
                                   command=data.command,
                                   binary=data.binary)

    def get_db(self):
        return self.db

    def print_statistics(self, timings):
        print(timings)
        print("N: {}".format(len(timings)))
        print("mean: {}".format(statistics.mean(timings)))
        print("stdev: {}".format(statistics.stdev(timings)))
        print("variance: {}".format(statistics.variance(timings)))
        print("min: {}".format(min(timings)))
        print("max: {}".format(max(timings)))
