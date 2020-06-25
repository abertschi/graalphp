#!/bin/env python
import os
import sys
import time
import subprocess
from datetime import date
from datetime import datetime


def run(exec_binary, script_path):
    print("=================================")
    print("+ benchmark for " + exec_binary)
    print("+ script: " + script_path)
    print("\n")
    
    total_start = int(round(time.time() * 1000 * 1000))
    subprocess.call([exec_binary, script_path])  
    total_end = int(round(time.time() * 1000* 1000))
    print("total us: %d" % (total_end - total_start))

    pass
    
def get_files(dir, file_ext):
    files = []
    for (dirpath, dirnames, filenames) in os.walk(dir):
        for f in filenames:
            if f.endswith(file_ext):
                files.append(os.path.join(dir, f))

    if not files:
        print("[!] file with ext not found " + bm_dir + " / " + file_ext)
    return files

def run_graalphp(bm_dir):
    GRAALPHP_HOME = os.environ.get('GRAALPHP_HOME')
    if not GRAALPHP_HOME:
        print("[!] GRAALPHP_HOME not set to run graalphp")
        return
    binary = os.path.join(GRAALPHP_HOME, "graalphp")
    for f in get_files(bm_dir, ".graalphp"):
        run(binary, f)
    pass


def run_php(bm_dir):
    for f in get_files(bm_dir, ".php"):
        run('php', f)
        
def run_sl(bm_dir):
    SL_HOME = os.environ.get('SL_HOME')
    if not SL_HOME:
        print("[!] SL_HOME not set to run simple language")
        return
    binary = os.path.join(SL_HOME, "sl")
    for f in get_files(bm_dir, ".sl"):
        run(binary, f)


def do_benchmarks():
    dir = os.path.dirname(os.path.realpath(__file__))
    fib_benchmark = os.path.join(dir, 'fib')
    print(datetime.now())

    run_php(fib_benchmark)
    run_graalphp(fib_benchmark)
    run_sl(fib_benchmark)


do_benchmarks()    
