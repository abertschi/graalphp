import datetime
import os
from os.path import join

from bench import Bench, verify_files

DIR = os.path.dirname(os.path.realpath(__file__))
SRC_FOLDER = os.path.join(DIR, 'bench/binary-trees')

# php
SRC_PHP_VAL = join(SRC_FOLDER, "binarytrees.php-3-val.php")
SRC_PHP_REF = join(SRC_FOLDER, "binarytrees.php-3-ref.php")

# gphp
SRC_GPHP_VAL = join(SRC_FOLDER, "binarytrees.php-3-val.graalphp")
SRC_GPHP_REF = join(SRC_FOLDER, "binarytrees.php-3-ref.graalphp")

# hack
SRC_HACK_VAL = join(SRC_FOLDER, "binarytrees.php-3-val.hack")

# jphp
SRC_JPHP_VAL = join(SRC_FOLDER, "binarytrees.php-3-val.jphp")
SRC_JPHP_REF = join(SRC_FOLDER, "binarytrees.php-3-ref.jphp")

TEST_BY_VAL = 'binary-trees-by-val'
TEST_BY_REF = 'binary-trees-by-ref'

verify_files([SRC_PHP_REF, SRC_GPHP_REF])
verify_files([SRC_PHP_VAL, SRC_GPHP_VAL, SRC_HACK_VAL])
verify_files([SRC_JPHP_REF, SRC_JPHP_VAL])

class BenchBinaryTrees(Bench):
    def run_by_ref(self):
        prefix = self.get_test_prefix()
        args_php = '-n -d memory_limit=4096M'

        res = []

        # run benchmark by reference (quick)
        res.append(self.run_php8(TEST_BY_REF, prefix, SRC_PHP_REF, args_php))
        res.append(self.run_php(TEST_BY_REF, prefix, SRC_PHP_REF, args_php))

        res.append(self.run_graalphp(TEST_BY_REF, prefix, SRC_GPHP_REF, ''))
        res.append(self.run_graalphp_native(TEST_BY_REF, prefix, SRC_GPHP_REF, ''))

        res.append(self.run_jphp(TEST_BY_REF, prefix, SRC_JPHP_REF, ''))

        self.extract_and_store_data_array(res)

    def run_by_val(self):
        prefix = self.get_test_prefix()
        args_php = '-n -d memory_limit=4096M'
        res = []

        # run benchmark by val
        res.append(self.run_hack(TEST_BY_VAL, prefix, SRC_HACK_VAL, ''))
        res.append(self.run_php8(TEST_BY_VAL, prefix, SRC_PHP_VAL, args_php))
        res.append(self.run_php(TEST_BY_VAL, prefix, SRC_PHP_VAL, args_php))

        res.append(self.run_jphp(TEST_BY_VAL, prefix, SRC_JPHP_VAL, ''))

        res.append(self.run_graalphp(TEST_BY_VAL, prefix, SRC_GPHP_VAL, ''))
        res.append(self.run_graalphp_native(TEST_BY_VAL, prefix, SRC_GPHP_VAL, ''))

        self.extract_and_store_data_array(res)

    def run(self):
        self.run_by_ref()
        self.run_by_val()

    def _import_data_manually(self):
        '/graalphp-source/benchmarks/scripts-report/measurements/2020-09-02T13:20:38.480351-binarytrees.php-3-val.graalphp-graalphp.txt'
        pref = '2020-09-02T13:20:38.480351'
        path = 'measurements/' + pref + '-binarytrees.php-3-val.graalphp-graalphp.txt'
        date = datetime.datetime(2020, 9, 2)
        self.import_data(path,
                         test_name=TEST_BY_VAL,
                         prefix=pref,
                         out_file_path=path,
                         src_file_path=path.replace('.txt', '-source.txt'),
                         date=date,
                         comment='manually imported, docker, no-turbo-2.7GHz-performance, report',
                         binary_version='graalphp 20.1.0-SNAPSHOT based on git cb59d053633d80b built at 2020-08-30-20:40:42.547.',
                         binary='graalphp')


if __name__ == '__main__':
    bm = BenchBinaryTrees()
    # bm.skip_graalphp = True
    # bm.skip_graalphp_native = True
    # bm.run_by_val()
    # bm.run_by_ref()
    # bm.run_jphp(TEST_BY_VAL, 'test', SRC_JPHP_VAL, '')
    bm._import_data_manually()
    pass
