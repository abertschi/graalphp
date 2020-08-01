import os
from os.path import join

from bench import BenchMeasurement, Bench, verify_files

DIR = os.path.dirname(os.path.realpath(__file__))
SRC_FOLDER = os.path.join(DIR, 'bench/fannkuchredux')

# php
SRC_PHP = join(SRC_FOLDER, "fannkuchredux.php-1.php")

# gphp
SRC_GPHP = join(SRC_FOLDER, "fannkuchredux.php-1.graalphp")

TEST = 'fannkuchredux-1'

verify_files([SRC_PHP, SRC_GPHP])


class BenchmarkFannkuch(Bench):

    def run(self):
        prefix = self.get_test_prefix()
        res = []

        res.append(self.run_php(TEST, prefix, SRC_PHP, ''))
        res.append(self.run_graalphp(TEST, prefix, SRC_GPHP, ''))
        res.append(self.run_graalphp_native(TEST, prefix, SRC_GPHP, ''))

        self.extract_and_store_data_array(res)

    def _import_data_manually(self):
        pref = '2020-07-31T19:18:04.649613'
        path = 'measurements/' + pref + '-fannkuchredux.php-1.graalphp-graalphp.txt'

        self.import_data(path,
                         test_name=TEST,
                         prefix=pref,
                         comment='graal 20.1.0',
                         binary='graalphp'
                         )

if __name__ == '__main__':
    bm = BenchmarkFannkuch()
    # bm._import_data_manually()

    pass
