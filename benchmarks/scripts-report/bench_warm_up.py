from bench import Bench
from bench_db import get_timings_by_id
from bench_fannkuch import BenchmarkFannkuch
from bench_graphs import do_warmup_plot
from bench_spectralnorm import BenchmarkSpectralNorm
from bench_binarytrees import BenchBinaryTrees

def collect_samples():
    i = 0
    n = 9
    i = 0
    Bench.comment = "iteration-analysis; docker, no-turbo-2.7GHz-performance"
    # while i < n:
    #     i = i + 1
    #     Bench.skip_all()
    #     Bench.skip_graalphp_native = False
    #     Bench.skip_graalphp = False
    #     BenchmarkFannkuch().run()
    # i = 0
    # while i < n:
    #     i = i + 1
    #     Bench.skip_all()
    #     Bench.skip_graalphp_native = False
    #     Bench.skip_graalphp = False
    #     BenchmarkSpectralNorm().run()
    # i = 0
    while i < n:
        i = i + 1
        Bench.skip_all()
        Bench.skip_graalphp_native = False
        Bench.skip_graalphp = False
        BenchBinaryTrees().run_by_ref()

if __name__ == '__main__':
    collect_samples()

    # Bench.comment = "docker, for report handin, no-turbo-2.7GHz-performance"
    # Bench.skip_all()
    # Bench.skip_graalphp_native = False
    # Bench.skip_graalphp = False
    # BenchmarkFannkuch().run()
    # BenchmarkSpectralNorm().run()




