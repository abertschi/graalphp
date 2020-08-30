from bench_db import export_to_csv


def binary_trees():
    ids = [
        78  # php
        , 93  # gphp val
        , 91  # gphp ref
        , 94  # gphpn val
        , 92  # gphpn ref
        , 77  # php 8
        , 79  # jphp
        , 76  # hhvm
        ,
        # by ref for PHP
        74,  # php 8
        75  # php
    ]

    export_to_csv(ids, warmup=5,
                  file_prefix='binary-trees')


def spectralnorm():
    ids = [
        96,  # gpnpn, val
        95,  # gpnp, val
        83,  # jpnp val
        82,  # php val
        81,  # pnp 8 val
        80,  # hhvm
        88,  # jphp ref
        87,  # PHP ref
        86,  # PHP 8 ref
        98,  # gphp native
        97  # gpnp
    ]

    export_to_csv(ids, warmup=5,
                  file_prefix='spectralnorm')


def fannkuchredux():
    ids = [
        90,  # gphp native
        89,  # gphp
        73,  # jphp
        72,  # php
        71,  # hhvm
        70  # php 8
    ]

    export_to_csv(ids, warmup=5,
                  file_prefix='fannkuchredux', sort=True, limit=True)


if __name__ == '__main__':
    binary_trees()
    # spectralnorm()
    # fannkuchredux()
