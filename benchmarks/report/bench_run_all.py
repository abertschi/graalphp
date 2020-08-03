from bench import Bench
from bench_fannkuch import BenchmarkFannkuch
from bench_binarytrees import BenchBinaryTrees
from bench_spectralnorm import BenchmarkSpectralNorm

if __name__ == '__main__':
    Bench.comment = "docker, hhvm, php8, initial, turbo"
    Bench.skip_all()
    Bench.skip_hack = False
    Bench.skip_php8 = False
    Bench.skip_php = False

    BenchmarkSpectralNorm().run()
    BenchmarkFannkuch().run()
    BenchBinaryTrees().run()
