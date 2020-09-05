from bench import Bench
from bench_fannkuch import BenchmarkFannkuch
from bench_binarytrees import BenchBinaryTrees
from bench_spectralnorm import BenchmarkSpectralNorm

if __name__ == '__main__':
    Bench.comment = "docker, no-turbo-2.7GHz-performance, report"
    Bench.skip_none()
    # Bench.skip_php = False
    BenchBinaryTrees().run()
    BenchmarkFannkuch().run()
    BenchmarkSpectralNorm().run()





    


