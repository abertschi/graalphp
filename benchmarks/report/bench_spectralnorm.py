import os
from os.path import join

from bench import BenchMeasurement, Bench

DIR = os.path.dirname(os.path.realpath(__file__))
SRC_FOLDER = os.path.join(DIR, 'bench/spectralnorm')

SRC_PHP = join(SRC_FOLDER, "spectralnorm.php-2.php")
SRC_PHP_UNMOD = join(SRC_FOLDER, "spectralnorm.php-2-php-unmodified.php")
SRC_PHP_BY_VAL = join(SRC_FOLDER, 'spectralnorm.php-2-pass-by-val.php')

# gphp
SRC_GPHP = join(SRC_FOLDER, "spectralnorm.php-2.graalphp")
SRC_GPHP_BY_VAL = join(SRC_FOLDER, 'spectralnorm.php-2-pass-by-val.graalphp')


class BenchSpectralNorm(Bench):

    def _create_tmp_data(self):
        bm0_gphp = ''
        lines = self.parse_values(bm0_gphp)
        timings = self.extract_timings(lines, None)
        print(timings)
        b = BenchMeasurement(
            test_name='spectralnorm-default',
            prefix=self.get_test_prefix('spectralnorm-default'),
            out_file='output',
            src_file='test',
            binary='php',
            command='commadn here',
            timings=timings)
        self.store_measurment(b)

    def run(self):
        prefix = self.get_test_prefix('spectralnorm')

        self.verify_files([SRC_PHP, SRC_PHP_UNMOD, SRC_PHP_BY_VAL,
                           SRC_GPHP, SRC_GPHP_BY_VAL])

        # bm 0, normal
        name = 'spectralnorm-by-ref'
        bm0_gphp = self.run_graalphp(name, prefix, SRC_GPHP)
        bm0_gphpn = self.run_graalphp_native(name, prefix, SRC_GPHP)
        bm0_php = self.run_php(name, prefix, SRC_PHP)
        self.extract_and_store_data(bm0_gphp)
        self.extract_and_store_data(bm0_gphpn)
        self.extract_and_store_data(bm0_php)

        # copy by value
        name = 'spectralnorm-by-val'
        bm1_gphp = self.run_graalphp(name, prefix, SRC_GPHP_BY_VAL)
        bm1_gphpn = self.run_graalphp_native(name, prefix, SRC_GPHP_BY_VAL)
        bm1_php = self.run_php(name, prefix, SRC_PHP_BY_VAL)
        self.extract_and_store_data(bm1_gphp)
        self.extract_and_store_data(bm1_gphpn)
        self.extract_and_store_data(bm1_php)

        # run unmodified source
        name = 'spectralnorm-unmod'
        bm2_php_unmod = self.run_php(name, prefix, SRC_PHP_UNMOD)
        self.extract_and_store_data(bm2_php_unmod)

    def extract_timings(self, lines, data_item: BenchMeasurement = None):
        timings = lines.iloc[:, 4].to_numpy()
        return timings


if __name__ == '__main__':
    BenchSpectralNorm().run()
