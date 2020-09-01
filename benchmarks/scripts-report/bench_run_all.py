from bench import Bench
from bench_fannkuch import BenchmarkFannkuch
from bench_binarytrees import BenchBinaryTrees
from bench_spectralnorm import BenchmarkSpectralNorm

if __name__ == '__main__':
    Bench.comment = "docker, no-turbo-2.7GHz-performance"
    Bench.skip_none()
    
    BenchmarkFannkuch().run()
    BenchBinaryTrees().run()
    BenchmarkSpectralNorm().run_by_ref()


