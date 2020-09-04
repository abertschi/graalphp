from bench import Bench
from bench_fannkuch import BenchmarkFannkuch
from bench_binarytrees import BenchBinaryTrees
from bench_spectralnorm import BenchmarkSpectralNorm

if __name__ == '__main__':
    Bench.comment = "docker, no-turbo-2.7GHz-performance, report"
    Bench.skip_all()
    Bench.skip_jphp = False
    BenchBinaryTrees().run_by_ref()

    Bench.skip_all()
    Bench.skip_php = False
    BenchmarkSpectralNorm().run()
    BenchmarkSpectralNorm().run()
    BenchmarkFannkuch().run()
    BenchmarkFannkuch().run()


    


