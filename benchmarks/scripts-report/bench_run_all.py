from bench import Bench
from bench_fannkuch import BenchmarkFannkuch
from bench_binarytrees import BenchBinaryTrees
from bench_spectralnorm import BenchmarkSpectralNorm

if __name__ == '__main__':
    Bench.comment = "docker, no-turbo-2.7GHz-performance, report"
    Bench.skip_all()
    Bench.skip_graalphp_native = False
    Bench.skip_graalphp = False
    # pending measurements
    # again: we only collected 11 samples, BenchBinaryTrees().run_by_val()

    # BenchmarkSpectralNorm().run_by_ref()
    # BenchmarkSpectralNorm().run_by_val()
    BenchBinaryTrees().run_by_val()


