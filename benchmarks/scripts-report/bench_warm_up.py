from bench import Bench
from bench_fannkuch import BenchmarkFannkuch
from bench_binarytrees import BenchBinaryTrees
from bench_spectralnorm import BenchmarkSpectralNorm

def iteration_time():
    i = 0
    n = 9
    i = 0
    Bench.comment = "iteration-analysis; docker, no-turbo-2.7GHz-performance"
    while i < n:
        i = i + 1
        Bench.skip_all()
        Bench.skip_graalphp_native = False
        Bench.skip_graalphp = False
        BenchmarkFannkuch().run()
    i = 0
    while i < n:
        i = i + 1
        Bench.skip_all()
        Bench.skip_graalphp_native = False
        Bench.skip_graalphp = False
        BenchmarkSpectralNorm().run()
    i = 0
    while i < n:
        i = i + 1
        Bench.skip_all()
        Bench.skip_graalphp_native = False
        Bench.skip_graalphp = False
        BenchBinaryTrees().run()


if __name__ == '__main__':
    # benchmarks()
    iteration_time()



