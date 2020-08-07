from bench import Bench
from bench_fannkuch import BenchmarkFannkuch
from bench_binarytrees import BenchBinaryTrees
from bench_spectralnorm import BenchmarkSpectralNorm

if __name__ == '__main__':
    Bench.comment = "docker, no-turbo-2.7GHz-performance"

    Bench.skip_none()
    Bench.skip_graalphp_native = True
    Bench.skip_graalphp = True
    
    BenchmarkFannkuch().run()
    BenchBinaryTrees().run()
    BenchmarkSpectralNorm().run()


