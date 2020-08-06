import os
import subprocess
import sys
from datetime import datetime
import pandas as pd
import csv
from io import StringIO
import statistics
from os.path import join
from bench_db import store_measurements
import shutil

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

HHVM_BINARY = 'hhvm'

# symlink created in docker image
PHP8_BINARY = 'php8'

# symlink created in docker image
JPHP_BINARY = 'jppm'

# JDK 14 path on graalphp-dev container image
JDK_14_HOME = '/usr/lib/jvm/java-14-openjdk-amd64/'

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
                 comment='',
                 commit='',
                 binary_version='',
                 timings=None):
        self.test_name = test_name
        self.out_file = out_file  # path
        self.src_file = src_file  # path
        self.binary = binary
        self.command = command
        self.prefix = prefix
        self.timings = timings
        self.comment = comment
        self.commit = commit
        self.binary_version = binary_version


def verify_file(path):
    if not os.path.exists(path):
        print("[!] file does not exist: " + path, flush=True)
        exit(1)


def verify_files(files):
    for f in files:
        verify_file(f)


def get_git_hash():
    hash = ''
    try:
        hash = subprocess.check_output(['git', 'rev-parse', 'HEAD']).decode(sys.stdout.encoding).rstrip()
        dirty = subprocess.check_output(['git', 'status', '--short']).decode(sys.stdout.encoding).rstrip()
        if dirty != "":
            hash += "-dirty"
    except Exception as e:
        print('ignoring exception')
        print(e)

    return hash


# TODO: add support for graalphp --version

class Bench:
    skip_php = True
    skip_php8 = True
    skip_hack = True
    skip_jphp = True

    skip_graalphp = False
    skip_graalphp_native = False

    # set the comment used when inserting data into db
    comment = None

    def __init__(self):
        pass

    @staticmethod
    def skip_all():
        Bench.skip_graalphp = True
        Bench.skip_graalphp_native = True
        Bench.skip_php = True
        Bench.skip_php8 = True
        Bench.skip_hack = True
        Bench.skip_jphp = True

    @staticmethod
    def skip_none():
        Bench.skip_graalphp = False
        Bench.skip_graalphp_native = False
        Bench.skip_php = False
        Bench.skip_php8 = False
        Bench.skip_hack = False
        Bench.skip_jphp = False

    def run_single_test(self,
                        bench_name,
                        file_prefix,
                        exec_name,
                        exec_binary,
                        exec_args,
                        exec_src,
                        comment='',
                        env_vars=None,
                        binary_version='',
                        cwd=None,
                        save_input_file=True):
        """
        Run a single benchmark

        :param bench_name: Name of benchmark
        :param file_prefix: prefix to group multiple tests with the same prefix
        :param exec_name: name of the exec binary
        :param exec_binary: exec binary to use
        :param exec_args: args for exec binary
        :param exec_src: src to execute
        :param comment: comment to write to database
        :param cwd: if not None, used as cwd when executing test
        :param save_input_file: (def: true) save exec_src to file for backup purpose
        :param binary_version: optional cmd to indicate bin version
        :param env_vars: commands to prefix before executing binary
        :return: BenchMeasurement
        """

        name = os.path.basename(exec_src)

        log_file = join(MEASUREMENT_DIR, '{}-{}-{}.txt'.format(file_prefix, name, exec_name))
        src_file = join(MEASUREMENT_DIR, '{}-{}-{}-source.txt'.format(file_prefix, name, exec_name))

        if save_input_file:
            print('saving input file: ' + exec_src)
            self._save_file(exec_src, src_file)

        if env_vars and env_vars != '':
            env_vars = env_vars + ' '

        exec = '{}{} {} {} | tee {}'.format(env_vars, exec_binary, exec_args, exec_src, log_file)
        print("[i] - running {} (cwd: {}, env_vars: {}, version: {})".
              format(exec, cwd if cwd else 'not-set',
                     env_vars,
                     binary_version), flush=True)
        if cwd:
            subprocess.call(exec, shell=True, cwd=cwd)
        else:
            subprocess.call(exec, shell=True)

        return BenchMeasurement(test_name=bench_name,
                                prefix=file_prefix,
                                command=exec,
                                out_file=log_file,
                                src_file=src_file,
                                binary=exec_name,
                                commit=get_git_hash(),
                                timings=[],
                                binary_version=binary_version,
                                comment=comment + ' ')

    def run_php(self, bench, prefix, src, args=''):
        if Bench.skip_php:
            print("skip_php=True")
            return None
        return self.run_single_test(bench, prefix, 'php', PHP_BINARY, args, src,
                                    binary_version=Bench._get_php_version(PHP_BINARY))

    def run_php8(self, bench, prefix, src, args=''):
        if Bench.skip_php8:
            print("skip_php8=True")
            return None
        if not self._binary_exists(PHP8_BINARY):
            print(PHP8_BINARY + " binary not found, skipping execution")
        return self.run_single_test(bench, prefix, 'php8', PHP8_BINARY, args, src,
                                    binary_version=Bench._get_php_version(PHP8_BINARY))

    def run_hack(self, bench, prefix, src, args=''):
        if Bench.skip_hack:
            print("skip_hack=True")
            return None
        if not self._binary_exists(HHVM_BINARY):
            print(HHVM_BINARY + " binary not found, skipping execution")
            return None

        # should be on already
        # a = ' -v Eval.Jit=1 '
        # args = a + args if args else a
        return self.run_single_test(bench, prefix, 'hhvm', HHVM_BINARY, args, src,
                                    binary_version=Bench._get_hhvm_version())

    def run_graalphp(self, bench, prefix, src, args=''):
        if Bench.skip_graalphp:
            print("skip_graalphp=True")
            return None
        return self.run_single_test(bench, prefix, 'graalphp', GRAALPHP_BINARY, args, src)

    def run_graalphp_native(self, bench, prefix, src, args=''):
        if Bench.skip_graalphp_native:
            print("skip_graalphp_native=True")
            return None
        return self.run_single_test(bench, prefix, 'graalphp-native', GRAALPHP_NATIVE_BINARY, args, src)

    # XXX: we dont execute the script directly. we call jphp start in the directory where package.php.yml is
    def run_jphp(self, bench, prefix, src, args=''):
        if Bench.skip_jphp:
            print("skip_jphp=True")
            return None
        if not self._binary_exists(JPHP_BINARY):
            print(JPHP_BINARY + " binary not found, skipping execution")
            return None

        env_vars = 'JAVA_HOME={}; '.format(JDK_14_HOME)
        if not os.path.exists(JDK_14_HOME):
            print('{} not found. skipping execution'.format(JDK_14_HOME))
            return None

        cmd_args = 'start ' + args
        return self.run_single_test(bench, prefix, 'jphp', JPHP_BINARY, cmd_args,
                                    exec_src='',
                                    cwd=src,
                                    save_input_file=False,
                                    env_vars=env_vars,
                                    binary_version=Bench._get_jphp_version())

    @staticmethod
    def _get_shell_cmd_result(cmd, remove_linebreaks=False):
        try:
            out = subprocess.Popen(cmd,
                                   stdout=subprocess.PIPE,
                                   stderr=subprocess.STDOUT, shell=True)
            stdout, stderr = out.communicate()
            out = stdout.decode(sys.stdout.encoding).rstrip()
            if remove_linebreaks:
                out = out.replace('\r', ' ').replace('\n', ' ')
            return out
        except Exception as e:
            print('ignoring exception')
            print(e)
            return ''

    @staticmethod
    def _get_php_version(exec):
        cmd = exec + ' --version | cut -d\( -f1 | head -n 1'
        return Bench._get_shell_cmd_result(cmd)

    @staticmethod
    def _get_jphp_version():
        return Bench._get_shell_cmd_result(JPHP_BINARY + ' version', remove_linebreaks=True)

    @staticmethod
    def _get_hhvm_version():
        return Bench._get_shell_cmd_result(HHVM_BINARY + ' --version', remove_linebreaks=True)

    def _binary_exists(self, bin_name):
        return shutil.which(bin_name) is not None

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
            if b:
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
                    date=None,
                    command='',
                    comment='',
                    binary='',
                    binary_version=''):
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
                           date=date,
                           command=command,
                           comment=comment,
                           confirm_store=True,
                           binary=binary,
                           binary_version=binary_version)

        self.print_statistics(timings)

    def store_measurment(self, data: BenchMeasurement):
        src_data = self.try_to_read_or_return_path(data.src_file) if data.src_file else ''
        out_data = self.try_to_read_or_return_path(data.out_file) if data.out_file else ''
        comment = data.comment + ' ' + Bench.comment if Bench.comment else data.comment
        store_measurements(test_name=data.test_name,
                           prefix=data.prefix,
                           timings=data.timings,
                           src_file=src_data,
                           out_file=out_data,
                           command=data.command,
                           commit=data.commit,
                           comment=comment,
                           binary_version=data.binary_version,
                           binary=data.binary)

    def get_db(self):
        return self.db

    def try_to_read_or_return_path(self, path):
        try:
            return self.read_file(path)
        except Exception as e:
            return path

    def print_statistics(self, timings):
        print(timings)
        print("N: {}".format(len(timings)))
        print("mean: {}".format(statistics.mean(timings)))
        print("stdev: {}".format(statistics.stdev(timings)))
        print("variance: {}".format(statistics.variance(timings)))
        print("min: {}".format(min(timings)))
        print("max: {}".format(max(timings)))


if __name__ == '__main__':
    print(Bench._get_php_version(PHP_BINARY))
    print(Bench._get_jphp_version())
    pass
