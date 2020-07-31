import os
from os.path import join

from bench import BenchMeasurement, Bench, verify_files

DIR = os.path.dirname(os.path.realpath(__file__))
SRC_FOLDER = os.path.join(DIR, 'bench/binary-trees')

# php
SRC_PHP_VAL = join(SRC_FOLDER, "binarytrees.php-3-val.php")
SRC_PHP_REF = join(SRC_FOLDER, "binarytrees.php-3-ref.php")

# gphp
SRC_GPHP_VAL = join(SRC_FOLDER, "binarytrees.php-3-val.graalphp")
SRC_GPHP_REF = join(SRC_FOLDER, "binarytrees.php-3-ref.graalphp")

TEST_BY_VAL = 'binary-trees-by-val'
TEST_BY_REF = 'binary-trees-by-ref'

verify_files([SRC_PHP_REF, SRC_GPHP_REF])
verify_files([SRC_PHP_VAL, SRC_GPHP_VAL])

class BenchBinaryTrees(Bench):

    def run_by_ref(self):
        prefix = self.get_test_prefix()
        args_php = '-n -d memory_limit=4096M'

        res = []

        # run benchmark by reference (quick)
        res.append(self.run_php(TEST_BY_REF, prefix, SRC_PHP_REF, args_php))
        res.append(self.run_graalphp(TEST_BY_REF, prefix, SRC_GPHP_REF, ''))
        res.append(self.run_graalphp_native(TEST_BY_REF, prefix, SRC_GPHP_REF, ''))

        self.extract_and_store_data_array(res)

    def run_by_val(self):
        prefix = self.get_test_prefix()
        args_php = '-n -d memory_limit=4096M'
        res = []

        # run benchmark by val
        res.append(self.run_php(TEST_BY_VAL, prefix, SRC_PHP_VAL, args_php))
        res.append(self.run_graalphp(TEST_BY_VAL, prefix, SRC_GPHP_VAL, ''))
        res.append(self.run_graalphp_native(TEST_BY_VAL, prefix, SRC_GPHP_VAL, ''))

        self.extract_and_store_data_array(res)

    def run(self):
        self.run_by_ref()
        self.run_by_val()

    def _import_data_manually(self):
        pref = '2020-07-31T01:08:00.473402-binary-trees'
        pref = pref + '-binarytrees.php-3-ref.'
        path = 'saved-measurements/20-07-31-graal-20.0.0-binary-trees/' + pref

        self.import_data(path + 'php-php.txt',
                         test_name='binary-trees-by-ref',
                         prefix=pref,
                         comment='graal 20.0.0',
                         binary='php')


if __name__ == '__main__':
    bm = BenchBinaryTrees()
    bm.run_by_val()
    bm.run_by_ref()

    # bm_import_data_manually()
    pass
