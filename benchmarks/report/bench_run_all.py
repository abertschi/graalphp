from bench import Bench
from bench_fannkuch import BenchmarkFannkuch
from bench_binarytrees import BenchBinaryTrees
from bench_spectralnorm import BenchmarkSpectralNorm

if __name__ == '__main__':
    Bench.comment = "docker, turbo, 4.0Ghz, performance"
    # always set governor: performance and clock rate to 4ghz

    Bench.skip_none()
    Bench.skip_graalphp_native = True
    Bench.skip_graalphp = True


    # Bench.skip_php8 = True
    # BenchmarkFannkuch().run()
    # Bench.skip_php8 = False

    # BenchBinaryTrees().run()
    BenchmarkSpectralNorm().run()


