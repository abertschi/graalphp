#!/bin/env python
import os
import sys
import time
import subprocess
from datetime import date
from datetime import datetime

def out(val):
    print(val, flush=True)
    

def run(exec_binary, script_path, args = None):
    out("=================================", )
    out("+ benchmark for " + exec_binary)
    out("+ script: " + script_path)
    out("\n")
    
    total_start = int(round(time.time() * 1000 * 1000))
    if args is None:
        subprocess.call([exec_binary, script_path])  
    else:
        process_args = [exec_binary] + args + [script_path]
        out(process_args)
        subprocess.call(process_args)      
    
    total_end = int(round(time.time() * 1000* 1000))
    out("total us: %d" % (total_end - total_start))
    pass
    
def get_files(dir, file_ext):
    files = []
    for (dirpath, dirnames, filenames) in os.walk(dir):
        for f in filenames:
            if f.endswith(file_ext):
                files.append(os.path.join(dir, f))

    if not files:
        out("[!] file with ext not found " + bm_dir + " / " + file_ext)
    return files

def run_graalphp(bm_dir):
    GRAALPHP_HOME = os.environ.get('GRAALPHP_HOME')
    if not GRAALPHP_HOME:
        out("[!] GRAALPHP_HOME not set to run graalphp")
        return
    binary = os.path.join(GRAALPHP_HOME, "graalphp")
    for f in get_files(bm_dir, ".graalphp"):
        run(binary, f)
    pass


def run_php(bm_dir, args=None):
    for f in get_files(bm_dir, ".php"):
        run('php', f, args)
        
def run_sl(bm_dir):
    SL_HOME = os.environ.get('SL_HOME')
    if not SL_HOME:
        out("[!] SL_HOME not set to run simple language")
        return
    binary = os.path.join(SL_HOME, "sl")
    for f in get_files(bm_dir, ".sl"):
        run(binary, f)


def do_fib_benchmark():
    dir = os.path.dirname(os.path.realpath(__file__))
    fib_benchmark = os.path.join(dir, 'fib')
    out(datetime.now())

    # run_php(fib_benchmark)
    run_graalphp(fib_benchmark)
    run_sl(fib_benchmark)


def do_fannkuchredux_benchmark():
    dir = os.path.dirname(os.path.realpath(__file__))
    fib_benchmark = os.path.join(dir, 'fannkuchredux')
    out(datetime.now())

    # run_php(fib_benchmark)
    run_graalphp(fib_benchmark)


def do_binarytrees_benchmark():
    dir = os.path.dirname(os.path.realpath(__file__))
    fib_benchmark = os.path.join(dir, 'binarytrees')
    out(datetime.now())


    run_graalphp(fib_benchmark)
    run_php(fib_benchmark, ['-n', '-d', 'memory_limit=4096M'])
    

# do_fib_benchmark()
# do_fannkuchredux_benchmark()
# do_binarytrees_benchmark()
do_fib_benchmark()
