#!/bin/env python
import os
import sys
import time
import subprocess
from datetime import date
from datetime import datetime
from datetime import datetime

GRAALPHP_HOME = os.environ.get('GRAALPHP_HOME')
if not GRAALPHP_HOME:
    print("[!] GRAALPHP_HOME not set to run graalphp", flush=True)
    exit(1)

GRAALPHP_BINARY = os.path.join(GRAALPHP_HOME, "graalphp")
GRAALPHP_NATIVE_BINARY = os.path.join(GRAALPHP_HOME, "graalphp-native/graalphp-native")
PHP_BINARY = 'php'
DIR = os.path.dirname(os.path.realpath(__file__))
MEASUREMENT_DIR = os.path.join(DIR, 'measurements')


def run_single_test(file_prefix, exec_name, exec_binary, exec_args, exec_src):
    log_file = os.path.join(MEASUREMENT_DIR, '{}-{}.txt'.format(file_prefix, exec_name))
    src_file = os.path.join(MEASUREMENT_DIR, '{}-{}-source.txt'.format(file_prefix, exec_name))
    save_file(exec_src, src_file)

    exec = '{} {} {} | tee {}'.format(exec_binary, exec_args, exec_src, log_file)
    print("[i] - running: " + exec, flush=True)
    subprocess.call(exec, shell=True)
    return log_file


def save_file(source, dest):
    file1 = open(source, "r")
    file2 = open(dest, "w")
    l = file1.readline()
    while l:
        file2.write(l)
        l = file1.readline()
    file1.close()
    file2.close()


def get_bench_time():
    return datetime.now().isoformat()


def get_test_prefix(name):
    return '{}-{}'.format(get_bench_time(), name)


def run_fannkuch_bench():
    prefix = get_test_prefix('fannkuch')
    fannkuch_folder = os.path.join(DIR, 'bench/fannkuchredux')

    php_src = os.path.join(fannkuch_folder, "fannkuchredux.php-1.php")
    graalphp_src = os.path.join(fannkuch_folder, "fannkuchredux.php-1.graalphp")

    
    graalphp_result = run_single_test(prefix, 'graalphp', GRAALPHP_BINARY, '', graalphp_src)
    graalphp_native_result = run_single_test(prefix, 'graalphp-native', GRAALPHP_NATIVE_BINARY, '', graalphp_src)
    php_result = run_single_test(prefix, 'php', PHP_BINARY, '', php_src)

    # process_fannkuch_bench(php_result)

def process_fannkuch_bench(path):
    print('[i] - processing ' + path)

    res = []
    min =  sys.maxsize
    max = 0
    N = 0
    sum = 0

    file = open(path, "r")
    l = file.readline()
    while l:

        l = file.readline()
    file.close()



def run_binary_trees():
    prefix = get_test_prefix('binary-trees')
    fannkuch_folder = os.path.join(DIR, 'bench/binary-trees')

    php_src = os.path.join(fannkuch_folder, "binarytrees.php-3.php")
    php_ref_src = os.path.join(fannkuch_folder, "binarytrees.php-3-ref.php")
    graalphp_src = os.path.join(fannkuch_folder, "binarytrees.php-3.graalphp")
    graalphp_ref_src = os.path.join(fannkuch_folder, "binarytrees.php-3-ref.graalphp")

    php_result = run_single_test(prefix, 'php', PHP_BINARY, '-n -d memory_limit=4096M', php_src)
    graalphp_result = run_single_test(prefix, 'graalphp', GRAALPHP_BINARY, '', graalphp_src)
    graalphp_native_result = run_single_test(prefix, 'graalphp-native', GRAALPHP_NATIVE_BINARY, '', graalphp_src)

    php_ref_result = run_single_test(prefix, 'php-ref', PHP_BINARY, '-n -d memory_limit=4096M', php_ref_src)
    graalphp_ref_result = run_single_test(prefix, 'graalphp-ref', GRAALPHP_BINARY, '', graalphp_ref_src)
    graalphp_native_ref_result = run_single_test(prefix, 'graalphp-native-ref', GRAALPHP_NATIVE_BINARY, '', graalphp_ref_src)

run_fannkuch_bench()
run_binary_trees()
