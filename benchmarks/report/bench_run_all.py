from bench import Bench
from bench_fannkuch import BenchmarkFannkuch
from bench_binarytrees import BenchBinaryTrees
from bench_spectralnorm import BenchmarkSpectralNorm

if __name__ == '__main__':
    Bench.comment = "docker, turbo"

    Bench.skip_none()
    Bench.skip_graalphp_native = True
    Bench.skip_graalphp = True

    BenchmarkFannkuch().run()
    BenchBinaryTrees().run()

    Bench.skip_all()
    Bench.skip_jphp = False
    BenchmarkSpectralNorm().run()


