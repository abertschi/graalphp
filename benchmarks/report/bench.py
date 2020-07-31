import os
import subprocess
from datetime import datetime
import pandas as pd
import csv
from io import StringIO
import statistics
from os.path import join
from bench_db import store_measurements

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
    test_name = ''
    prefix = ''
    out_file = ''
    src_file = ''
    binary = ''
    command = ''
    comment = ''
    timings = []

    def __init__(self,
                 test_name,
                 prefix,
                 out_file='',
                 src_file='',
                 binary='',
                 command='',
                 comment='',
                 timings=None):
        self.test_name = test_name
        self.out_file = out_file
        self.src_file = src_file
        self.binary = binary
        self.command = command
        self.prefix = prefix
        self.timings = timings
        self.comment = comment


def verify_file(path):
    if not os.path.exists(path):
        print("[!] file does not exist: " + path, flush=True)
        exit(1)


def verify_files(files):
    for f in files:
        verify_file(f)


class Bench:

    def __init__(self):
        pass


    def get_git_revision_hash(self):
        return subprocess.check_output(['git', 'rev-parse', 'HEAD'])

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

    def get_test_prefix(self):
        return '{}'.format(self.get_bench_time())

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
        timings = lines.iloc[:, 4].to_numpy()
        return timings

    def extrat_data(self, data: BenchMeasurement):
        file = data.out_file
        lines = self.parse_values(file)
        timings = self.extract_timings(lines, data)
        self.print_statistics(timings)
        data.timings = timings
        return data

    def extract_and_store_data_array(self, data_arr: [BenchMeasurement]):
        for b in data_arr:
            self.extract_and_store_data(b)

    def extract_and_store_data(self, data: BenchMeasurement):
        self.extrat_data(data)
        self.store_measurment(data)

    def import_data(self,
                    path,
                    test_name='',
                    prefix='',
                    src_file_path=None,
                    out_file_path=None,
                    command='',
                    comment='',
                    binary=''):
        verify_file(path)
        lines = self.parse_values(path)
        timings = self.extract_timings(lines, None)
        src_file = self.read_file(src_file_path) if src_file_path else ''
        out_file = self.read_file(out_file_path) if out_file_path else ''

        store_measurements(test_name=test_name,
                           prefix=prefix,
                           timings=timings,
                           src_file=src_file,
                           out_file=out_file,
                           command=command,
                           comment=comment,
                           binary=binary)

        self.print_statistics(timings)

    def store_measurment(self, data: BenchMeasurement):
        store_measurements(test_name=data.test_name,
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
