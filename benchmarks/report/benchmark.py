#!/bin/env python
import os
import sys
import time
import subprocess
from datetime import date
from datetime import datetime
from datetime import datetime

import pandas as pd
import csv
from io import StringIO
import statistics

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
    name = os.path.basename(exec_src)
    log_file = os.path.join(MEASUREMENT_DIR, '{}-{}-{}.txt'.format(file_prefix, name, exec_name))
    src_file = os.path.join(MEASUREMENT_DIR, '{}-{}-{}-source.txt'.format(file_prefix, name, exec_name))
    save_file(exec_src, src_file)

    exec = '{} {} {} | tee {}'.format(exec_binary, exec_args, exec_src, log_file)
    print("[i] - running: " + exec, flush=True)
    subprocess.call(exec, shell=True)
    return log_file


def run_php(prefix, src, args=''):
    return run_single_test(prefix, 'php', PHP_BINARY, args, src)


def run_graalphp(prefix, src, args=''):
    return run_single_test(prefix, 'graalphp', GRAALPHP_BINARY, args, src)


def run_graalphp_and_native(prefix, src, args=''):
    run_graalphp(prefix, args, src)
    run_graalphp_native(prefix, args, src)


def run_graalphp_native(prefix, src, args=''):
    return run_single_test(prefix, 'graalphp-native', GRAALPHP_NATIVE_BINARY, args, src)


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
    graalphp_native_ref_result = run_single_test(prefix, 'graalphp-native-ref', GRAALPHP_NATIVE_BINARY, '',
                                                 graalphp_ref_src)


from os.path import join


def run_spectral():
    prefix = get_test_prefix('spectralnorm')
    folder = os.path.join(DIR, 'bench/spectralnorm')

    # php
    php_src = join(folder, "spectralnorm.php-2.php")
    php_src_unmod = join(folder, "spectralnorm.php-2-php-unmodified.php")
    php_src_pass_by_val = join(folder, 'spectralnorm.php-2-pass-by-val.php')

    # gphp
    gphp_src = join(folder, "spectralnorm.php-2.graalphp")
    gphp_src_pass_by_val = join(folder, 'spectralnorm.php-2-pass-by-val.graalphp')

    # run
    run_graalphp(prefix, gphp_src, '')
    run_graalphp_native(prefix, gphp_src, '')

    run_graalphp(prefix, gphp_src_pass_by_val, '')
    run_graalphp_native(prefix, gphp_src_pass_by_val, '')

    run_php(prefix, php_src, '')
    run_php(prefix, php_src_unmod, '')
    run_php(prefix, php_src_pass_by_val, '')


def parse_values(path):
    pd.set_option('display.max_rows', None)
    file = open(path, "r")
    l = file.readline()
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


def unify_binary_trees(columns):
    timings = columns.iloc[:, 4].to_numpy()
    return timings


def extract_measurements(timings):
    # print(timings)
    timings = timings[15:]
    print(timings)

    print("N: {}".format(len(timings)))
    print("mean: {}".format(statistics.mean(timings)))
    print("stdev: {}".format(statistics.stdev(timings)))
    print("variance: {}".format(statistics.variance(timings)))
    print("min: {}".format(min(timings)))
    print("max: {}".format(max(timings)))


def get_timings(path):
    return unify_binary_trees(parse_values(path))


import numpy as np
import matplotlib.pyplot as plt
from os import walk


def fannkuch():
    prefix = 'saved-measurements/20-07-20/'
    php = '2020-07-20T02:38:05.827873-fannkuch-php.txt'
    graalphp = '2020-07-20T02:38:05.827873-fannkuch-graalphp.txt'
    graalphp_native = '2020-07-20T02:38:05.827873-fannkuch-graalphp-native.txt'
    warmup_thres = 10

    php_val = get_timings(os.path.join(prefix, php))
    php_mean = statistics.mean(php_val)

    graalphp_val = get_timings(os.path.join(prefix, graalphp))[warmup_thres:]
    graalphp_native_val = get_timings(os.path.join(prefix, graalphp_native))[warmup_thres:]

    graalphp_val = graalphp_val / php_mean
    graalphp_native_val = graalphp_native_val / php_mean

    php_val = php_val / php_mean

    php_mean = statistics.mean(php_val)
    graalphp_mean = statistics.mean(graalphp_val)
    graalphp_native_mean = statistics.mean(graalphp_native_val)

    speedup = [php_mean, php_mean / graalphp_mean, php_mean / graalphp_native_mean]

    # Calculate the standard deviation
    php_std = statistics.stdev(php_val)
    graalphp_std = statistics.stdev(graalphp_val)
    graalphp_nat_std = statistics.stdev(graalphp_native_val)

    # Define labels, positions, bar heights and error bar heights
    labels = ['php', 'graalphp', 'graalphp-native']
    x_pos = np.arange(len(labels))
    CTEs = speedup
    # CTEs = [fk_php_mean, fk_graalphp_mean, fk_graalphp_nat_mean]
    error = [php_std, graalphp_std, graalphp_nat_std]

    # Build the plot
    fig, ax = plt.subplots()
    ax.bar(x_pos, CTEs,
           yerr=error,
           align='center',
           alpha=0.5,
           ecolor='black',
           capsize=10)
    ax.set_ylabel('Speedup After Warmup')
    ax.set_xticks(x_pos)
    ax.set_xticklabels(labels)
    ax.set_title('Fannkuch Benchmark')
    ax.yaxis.grid(True)

    # Save the figure and show
    plt.tight_layout()
    plt.savefig('fannkuch.png')
    plt.show()


def binary_trees():
    prefix = 'saved-measurements/20-07-20/'
    php = '2020-07-19T22:03:31.585475-binary-trees-php.txt'
    graalphp = '2020-07-19T22:03:31.585475-binary-trees-graalphp.txt'
    graalphp_native = '2020-07-19T22:03:31.585475-binary-trees-graalphp-native.txt'
    warmup_thres = 10

    php_val = get_timings(os.path.join(prefix, php))
    php_mean = statistics.mean(php_val)

    graalphp_val = get_timings(os.path.join(prefix, graalphp))[warmup_thres:]
    graalphp_native_val = get_timings(os.path.join(prefix, graalphp_native))[warmup_thres:]

    graalphp_val = graalphp_val / php_mean
    graalphp_native_val = graalphp_native_val / php_mean

    php_val = php_val / php_mean

    php_mean = statistics.mean(php_val)
    graalphp_mean = statistics.mean(graalphp_val)
    graalphp_native_mean = statistics.mean(graalphp_native_val)

    speedup = [php_mean, php_mean / graalphp_mean, php_mean / graalphp_native_mean]

    # Calculate the standard deviation
    php_std = statistics.stdev(php_val)
    graalphp_std = statistics.stdev(graalphp_val)
    graalphp_nat_std = statistics.stdev(graalphp_native_val)

    # Define labels, positions, bar heights and error bar heights
    labels = ['php', 'graalphp', 'graalphp-native']
    x_pos = np.arange(len(labels))
    CTEs = speedup
    # CTEs = [fk_php_mean, fk_graalphp_mean, fk_graalphp_nat_mean]
    error = [php_std, graalphp_std, graalphp_nat_std]

    # Build the plot
    fig, ax = plt.subplots()
    ax.bar(x_pos, CTEs,
           yerr=error,
           align='center',
           alpha=0.5,
           ecolor='black',
           capsize=10)
    ax.set_ylabel('Speedup After Warmup')
    ax.set_xticks(x_pos)
    ax.set_xticklabels(labels)
    ax.set_title('Binary Tree Benchmark (copy by ref)')
    ax.yaxis.grid(True)

    # Save the figure and show
    plt.tight_layout()
    plt.savefig('binary-trees.png')
    plt.show()



def plot_speedup_box(title, save_name, php_val, gphp_val, gphp_native_val, warmup_thres=0):
    php_mean = statistics.mean(php_val)
    graalphp_val = gphp_val / php_mean
    graalphp_native_val = gphp_native_val / php_mean

    php_val = php_val / php_mean

    labels = ['php', 'graalphp', 'graalphp-native']
    times = [1 / php_val, 1 / graalphp_val, 1 / graalphp_native_val]

    fig = plt.figure()
    ax = fig.gca()
    boxplot = ax.boxplot(times, vert=True, patch_artist=False)

    ax.set_ylabel('Speedup After Warmup')
    ax.set_xticklabels(labels)
    ax.set_title(title)
    ax.yaxis.grid(True)
    ax.set_axisbelow(True)
    plt.savefig(save_name)
    plt.show()
    pass

def plot_speedup(title, save_name, php_val, gphp_val, gphp_native_val, warmup_thres=0):
    php_mean = statistics.mean(php_val)

    graalphp_val = gphp_val / php_mean
    graalphp_native_val = gphp_native_val / php_mean

    php_val = php_val / php_mean

    php_mean = statistics.mean(php_val)
    graalphp_mean = statistics.mean(graalphp_val)
    graalphp_native_mean = statistics.mean(graalphp_native_val)

    speedup = [php_mean, php_mean / graalphp_mean, php_mean / graalphp_native_mean]

    # Calculate the standard deviation
    php_std = statistics.stdev(php_val)
    graalphp_std = statistics.stdev(graalphp_val)
    graalphp_nat_std = statistics.stdev(graalphp_native_val)

    # Define labels, positions, bar heights and error bar heights
    labels = ['php', 'graalphp', 'graalphp-native']
    x_pos = np.arange(len(labels))
    CTEs = speedup
    # CTEs = [fk_php_mean, fk_graalphp_mean, fk_graalphp_nat_mean]
    error = [php_std, graalphp_std, graalphp_nat_std]

    # Build the plot
    fig, ax = plt.subplots()
    ax.bar(x_pos, CTEs,
           yerr=error,
           align='center',
           alpha=0.5,
           ecolor='black',
           capsize=10)
    ax.set_ylabel('Speedup After Warmup')
    ax.set_xticks(x_pos)
    ax.set_xticklabels(labels)
    ax.set_title(title)
    ax.yaxis.grid(True)

    # Save the figure and show
    plt.tight_layout()
    plt.savefig(save_name)
    plt.show()


def spectralnorm_speedup():
    prefix = 'saved-measurements/20-07-27/2020-07-26T22:24:05.785564-spectralnorm-'
    php = 'spectralnorm.php-2.php-php.txt'
    graalphp = 'spectralnorm.php-2.graalphp-graalphp.txt'
    graalphp_native = 'spectralnorm.php-2.graalphp-graalphp-native.txt'
    warmup_thres = 10

    php_val = get_timings(prefix + php)
    graalphp_val = get_timings(prefix + graalphp)[warmup_thres:]
    graalphp_native_val = get_timings(prefix + graalphp_native)[warmup_thres:]

    plot_speedup('Spectral Norm Benchmark', 'spectral-norm.png', php_val, graalphp_val, graalphp_native_val)
    plot_speedup_box('Spectral Norm Benchmark', 'spectral-norm-boxplot.png', php_val, graalphp_val, graalphp_native_val)


def boxplot_fannkuchen():
    prefix = 'saved-measurements/20-07-20/'
    php = '2020-07-20T02:38:05.827873-fannkuch-php.txt'
    graalphp = '2020-07-20T02:38:05.827873-fannkuch-graalphp.txt'
    graalphp_native = '2020-07-20T02:38:05.827873-fannkuch-graalphp-native.txt'
    warmup_thres = 10

    php_val = get_timings(os.path.join(prefix, php))
    php_mean = statistics.mean(php_val)

    graalphp_val = get_timings(os.path.join(prefix, graalphp))[warmup_thres:]
    graalphp_native_val = get_timings(os.path.join(prefix, graalphp_native))[warmup_thres:]

    graalphp_val = graalphp_val / php_mean
    graalphp_native_val = graalphp_native_val / php_mean

    php_val = php_val / php_mean

    labels = ['php', 'graalphp', 'graalphp-native']
    times = [1 / php_val, 1 / graalphp_val, 1 / graalphp_native_val]

    fig = plt.figure()
    ax = fig.gca()
    boxplot = ax.boxplot(times, vert=True, patch_artist=False)

    # cmap = plt.cm.get_cmap("rainbow")
    # colors = [cmap(val / len(times)) for val in range(len(times))]

    # for patch, color in zip(boxplot["boxes"], colors):
    #     patch.set_facecolor(color)

    # plt.title('Benchmark Binary Trees after Warmup')
    # plt.legend(handles=boxplot["boxes"], labels=labels, fontsize="small")
    # plt.ylabel("Speedup")

    ax.set_ylabel('Speedup After Warmup')
    ax.set_xticklabels(labels)
    ax.set_title('Fannkuch Benchmark')
    ax.yaxis.grid(True)
    ax.set_axisbelow(True)
    plt.savefig('fannkuch.boxplot.png')
    plt.show()


def boxplot_binary_trees():
    prefix = 'saved-measurements/20-07-20/'
    php = '2020-07-19T22:03:31.585475-binary-trees-php.txt'
    graalphp = '2020-07-19T22:03:31.585475-binary-trees-graalphp.txt'
    graalphp_native = '2020-07-19T22:03:31.585475-binary-trees-graalphp-native.txt'
    warmup_thres = 10

    php_val = get_timings(os.path.join(prefix, php))
    php_mean = statistics.mean(php_val)

    graalphp_val = get_timings(os.path.join(prefix, graalphp))[warmup_thres:]
    graalphp_native_val = get_timings(os.path.join(prefix, graalphp_native))[warmup_thres:]

    graalphp_val = graalphp_val / php_mean
    graalphp_native_val = graalphp_native_val / php_mean

    php_val = php_val / php_mean

    labels = ['php', 'graalphp', 'graalphp-native']
    times = [1 / php_val, 1 / graalphp_val, 1 / graalphp_native_val]

    fig = plt.figure()
    ax = fig.gca()
    boxplot = ax.boxplot(times, vert=True, patch_artist=False)

    # cmap = plt.cm.get_cmap("rainbow")
    # colors = [cmap(val / len(times)) for val in range(len(times))]

    # for patch, color in zip(boxplot["boxes"], colors):
    #     patch.set_facecolor(color)

    # plt.title('Benchmark Binary Trees after Warmup')
    # plt.legend(handles=boxplot["boxes"], labels=labels, fontsize="small")
    # plt.ylabel("Speedup")

    ax.set_ylabel('Speedup After Warmup')
    ax.set_xticklabels(labels)
    ax.set_title('Binary Tree Benchmark (copy by ref)')
    ax.yaxis.grid(True)
    ax.set_axisbelow(True)
    plt.savefig('binary-trees.boxplot.png')
    plt.show()


# boxplot_binary_trees()
# boxplot_fannkuchen()
# fannkuch()
# binary_trees()
# run_spectral()
spectralnorm_speedup()
