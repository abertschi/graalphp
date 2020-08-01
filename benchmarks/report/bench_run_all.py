from bench import Bench
from bench_fannkuch import BenchmarkFannkuch
from bench_binarytrees import BenchBinaryTrees
from bench_spectralnorm import BenchmarkSpectralNorm


if __name__ == '__main__':
    # Bench.skip_php = True
    Bench.comment = "intel turbo disabled, graal 20.1.0"

    BenchmarkFannkuch().run()
    BenchBinaryTrees().run()
    BenchmarkSpectralNorm().run()
