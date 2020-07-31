import os
from os.path import join

from bench import BenchMeasurement, Bench, verify_files, verify_file

DIR = os.path.dirname(os.path.realpath(__file__))
SRC_FOLDER = os.path.join(DIR, 'bench/spectralnorm')

# php
SRC_PHP_VAL = join(SRC_FOLDER, "spectralnorm.php-2-val.php")
SRC_PHP_REF = join(SRC_FOLDER, "spectralnorm.php-2-ref.php")
SRC_PHP_UNMOD = join(SRC_FOLDER, "spectralnorm.php-2-unmodified.php")

# gphp
SRC_GPHP_VAL = join(SRC_FOLDER, "spectralnorm.php-2-val.graalphp")
SRC_GPHP_REF = join(SRC_FOLDER, "spectralnorm.php-2-ref.graalphp")

TEST_BY_VAL = 'spectralnorm-by-val'
TEST_BY_REF = 'spectralnorm-by-ref'
TEST_BY_UNMOD = 'spectralnorm-unmod'

verify_files([SRC_PHP_VAL, SRC_PHP_REF, SRC_PHP_UNMOD])
verify_files([SRC_GPHP_VAL, SRC_GPHP_REF])


class BenchmarkSpectralNorm(Bench):

    def run_by_ref(self):
        prefix = self.get_test_prefix()
        res = []

        res.append(self.run_php(TEST_BY_REF, prefix, SRC_PHP_REF, ''))
        res.append(self.run_graalphp(TEST_BY_REF, prefix, SRC_GPHP_REF, ''))
        res.append(self.run_graalphp_native(TEST_BY_REF, prefix, SRC_GPHP_REF, ''))

        self.extract_and_store_data_array(res)

    def run_by_val(self):
        prefix = self.get_test_prefix()
        res = []

        res.append(self.run_php(TEST_BY_VAL, prefix, SRC_PHP_VAL, ''))
        res.append(self.run_graalphp(TEST_BY_VAL, prefix, SRC_GPHP_VAL, ''))
        res.append(self.run_graalphp_native(TEST_BY_VAL, prefix, SRC_GPHP_VAL, ''))
        res.append(self.run_php(TEST_BY_UNMOD, prefix, SRC_PHP_UNMOD, ''))

        self.extract_and_store_data_array(res)

    def run(self):
        self.run_by_ref()
        self.run_by_val()

    def extract_timings(self, lines, data_item: BenchMeasurement = None):
        timings = lines.iloc[:, 4].to_numpy()
        return timings

    def _import_data_manually(self):
        pref = '2020-07-26T22:24:05.785564'
        pref = pref + '-spectralnorm-spectralnorm.php-2-'
        path = DIR + '/saved-measurements/20-07-20/' + pref


        self.import_data(path + 'php-unmodified.php-php.txt',
                         # src_file_path= path + 'php-unmodified.php-php-source.txt',
                         test_name=TEST_BY_UNMOD,
                         prefix=pref,
                         comment='graal 20.0.0',
                         binary='php',
                         )


if __name__ == '__main__':
    bm = BenchmarkSpectralNorm()
    # bm.run_by_val()
    # bm.run_by_ref()

    bm._import_data_manually()
    pass