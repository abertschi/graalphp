from bench_db import export_to_csv_nested

def binary_trees():
    ids = [
        # php
        [
            78  # binary-trees-by-val 2020-08-08 03:50,  php
            , 213  # binary-trees-by-val 2020-09-03 23:04,  php
            , 225  #  binary-trees-by-val, 2020-09-05 00:00: 00 | php
        ]
        , [77, 212]  # php 8
        , [76, 211]  # hhvm
        , [79, 217]  # jphp by val
        #
        # jphp by ref
        , [
            219,  # binary-trees-by-ref |      | 2020-09-04 17:09:55.844551 |       jphp
            220  # binary-trees-by-ref |      | 2020-09-04 22:53:54.055114 |       jphp
        ]
        # gphpn val
        , [198, 191, 156, 160, 164]
        # graalphp val
        , [
            155  # binary-trees-by-val, 2020-09-01 01:57:0, graalphp
            , 159  # binary-trees-by-val, 2020-09-01 07:20:34, graalphp
            , 163  # binary-trees-by-val, 2020-09-01 12:48:38, graalphp
            , 196  # binary-trees-by-val,    2020-09-02     , graalphp
            , 190  # binary-trees-by-val, 2020-09-02 09:36:24, graalphp
            , 197  # binary-trees-by-val, 2020-09-03 04:18:03, graalphp
        ]
        # gphp ref
        , [
            153  # binary-trees-by-ref, 2020-08-31 20:38, graalphp
            , 157  # binary-trees-by-ref, 2020-09-01 02:04, graalphp
            , 161  # binary-trees-by-ref, 2020-09-01 07:27, graalphp
            , 165  # binary-trees-by-ref, 2020-09-01 12:56, graalphp
            , 167  # binary-trees-by-ref, 2020-09-01 14:29, graalphp
            , 168  # binary-trees-by-ref, 2020-09-01 14:40, graalphp
            , 170  # binary-trees-by-ref, 2020-09-01 14:48, graalphp
            , 172  # binary-trees-by-ref, 2020-09-01 14:55, graalphp
            , 174  # binary-trees-by-ref, 2020-09-01 15:03, graalphp
            , 176  # binary-trees-by-ref, 2020-09-01 15:10, graalphp
            , 178  # binary-trees-by-ref, 2020-09-01 15:18, graalphp
            , 180  # binary-trees-by-ref, 2020-09-01 15:25, graalphp
            , 182  # binary-trees-by-ref, 2020-09-01 15:33, graalphp
            , 184  # binary-trees-by-ref, 2020-09-01 15:40, graalphp
            , 188  # binary-trees-by-ref, 2020-09-02 01:10, graalphp
        ]
        # gphpn ref
        , [
            154  # binary-trees-by-ref, 2020-08-31, graalphp-native |  3  |   51.8
            , 158  # binary-trees-by-ref, 2020-09-01, graalphp-native |  3  |  50.68
            , 162  # binary-trees-by-ref, 2020-09-01, graalphp-native |  3  |   50.8
            , 166  # binary-trees-by-ref, 2020-09-01, graalphp-native |  3  |  52.26
            , 169  # binary-trees-by-ref, 2020-09-01, graalphp-native |  3  |  51.23
            , 171  # binary-trees-by-ref, 2020-09-01, graalphp-native |  3  |  51.31
            , 173  # binary-trees-by-ref, 2020-09-01, graalphp-native |  3  |  50.94
            , 175  # binary-trees-by-ref, 2020-09-01, graalphp-native |  3  |  51.35
            , 177  # binary-trees-by-ref, 2020-09-01, graalphp-native |  3  |  52.19
            , 179  # binary-trees-by-ref, 2020-09-01, graalphp-native |  3  |  51.39
            , 181  # binary-trees-by-ref, 2020-09-01, graalphp-native |  3  |  52.24
            , 183  # binary-trees-by-ref, 2020-09-01, graalphp-native |  3  |  51.29
            , 185  # binary-trees-by-ref, 2020-09-01, graalphp-native |  3  |  52.21
            , 189  # binary-trees-by-ref, 2020-09-02, graalphp-native |  45 |  51.69
        ]
        # by ref for PHP
        , [74, 209]  # php 8 by ref
        , [75, 210]  # php by ref
    ]

    # todo: add jphp by ref benchmark
    export_to_csv_nested(ids, warmup=5,
                         file_prefix='binary-trees')


def spectralnorm():
    ids = [
        # by val
        # php
        [
            82  # spectralnorm-by-val, 2020-08-08 07:55:, php
            , 204  # spectralnorm-by-val, 2020-09-03 16:18:, php
            , 222  # | spectralnorm-by-val2020 - 09-05 03:26: 50.476972 | php
        ],
        # pnp 8 val
        [
            81  # spectralnorm-by-val, 2020-08-08 07:55, php8
            , 203  # spectralnorm-by-val, 2020-09-03 16:18, php8
        ],
        # hhvm
        [
            80  # spectralnorm-by-val, 2020-08-08 07:55, hhvm
            , 202  # spectralnorm-by-val, 2020-09-03 16:18, hhvm
        ],
        # jphp
        [
            83  # spectralnorm-by-val, 2020-08-08 07:55, jphp
            , 215  # spectralnorm-by-val, 2020-09-04 03:56, jphp
        ],
        # graalphp val
        [
            117  # spectralnorm-by-val, 2020-08-31 00:31, graalphp,
            , 121  # spectralnorm-by-val, 2020-08-31 00:37, graalphp,
            , 125  # spectralnorm-by-val, 2020-08-31 00:43, graalphp,
            , 129  # spectralnorm-by-val, 2020-08-31 00:49, graalphp,
            , 133  # spectralnorm-by-val, 2020-08-31 00:55, graalphp,
            , 137  # spectralnorm-by-val, 2020-08-31 01:01, graalphp,
            , 141  # spectralnorm-by-val, 2020-08-31 01:07, graalphp,
            , 145  # spectralnorm-by-val, 2020-08-31 01:13, graalphp,
            , 149  # spectralnorm-by-val, 2020-08-31 01:19, graalphp,
            , 194  # spectralnorm-by-val, 2020-09-02 13:20, graalphp,
        ],
        # graalphp native val
        [
            118  # spectralnorm-by-val, 2020-08-31 00:31, graalphp-native
            , 122  # spectralnorm-by-val, 2020-08-31 00:37, graalphp-native
            , 126  # spectralnorm-by-val, 2020-08-31 00:43, graalphp-native
            , 130  # spectralnorm-by-val, 2020-08-31 00:49, graalphp-native
            , 134  # spectralnorm-by-val, 2020-08-31 00:55, graalphp-native
            , 138  # spectralnorm-by-val, 2020-08-31 01:01, graalphp-native
            , 142  # spectralnorm-by-val, 2020-08-31 01:07, graalphp-native
            , 146  # spectralnorm-by-val, 2020-08-31 01:13, graalphp-native
            , 150  # spectralnorm-by-val, 2020-08-31 01:19, graalphp-native
            , 195  # spectralnorm-by-val, 2020-09-02 13:20, graalphp-native
        ],
        # by ref
        # php ref
        [
            87  # spectralnorm-by-ref, 2020-08-08 10:20, php
            , 208  # spectralnorm-by-ref, 2020-09-03 17:17, php
            , 224  # spectralnorm - by - ref | | 2020 - 09 - 05 04:05: 11.811245 | php
        ],
        # php 8 by ref
        [
            86  # spectralnorm-by-ref, 2020-08-08 10:20, php8
            , 207  # spectralnorm-by-ref, 2020-09-03 17:17, php8
        ],
        # jphp by ref
        [
            88  # spectralnorm-by-ref, 2020-08-08 10:20:56.755619 ,  jphp
            , 216  # spectralnorm-by-ref, 2020-09-04 05:24:26.827737 ,  jphp
        ],
        # graalphp by ref
        [
            119  # spectralnorm-by-ref, 2020-08-31 00:34, graalphp
            , 123  # spectralnorm-by-ref, 2020-08-31 00:40, graalphp
            , 127  # spectralnorm-by-ref, 2020-08-31 00:46, graalphp
            , 131  # spectralnorm-by-ref, 2020-08-31 00:52, graalphp
            , 135  # spectralnorm-by-ref, 2020-08-31 00:58, graalphp
            , 139  # spectralnorm-by-ref, 2020-08-31 01:04, graalphp
            , 143  # spectralnorm-by-ref, 2020-08-31 01:10, graalphp
            , 147  # spectralnorm-by-ref, 2020-08-31 01:16, graalphp
            , 151  # spectralnorm-by-ref, 2020-08-31 01:22, graalphp
            , 192  # spectralnorm-by-ref, 2020-09-02 12:51, graalphp
        ],
        # graalphp-native by ref
        [
            120  # spectralnorm-by-ref, 2020-08-31 00:34, graalphp-native
            , 124  # spectralnorm-by-ref, 2020-08-31 00:40, graalphp-native
            , 128  # spectralnorm-by-ref, 2020-08-31 00:46, graalphp-native
            , 132  # spectralnorm-by-ref, 2020-08-31 00:52, graalphp-native
            , 136  # spectralnorm-by-ref, 2020-08-31 00:58, graalphp-native
            , 140  # spectralnorm-by-ref, 2020-08-31 01:04, graalphp-native
            , 144  # spectralnorm-by-ref, 2020-08-31 01:10, graalphp-native
            , 148  # spectralnorm-by-ref, 2020-08-31 01:16, graalphp-native
            , 152  # spectralnorm-by-ref, 2020-08-31 01:22, graalphp-native
            , 193  # spectralnorm-by-ref, 2020-09-02 12:51, graalphp-native
        ]
    ]

    export_to_csv_nested(ids, warmup=5,
                         file_prefix='spectralnorm')


def fannkuchredux():
    ids = [
        # php
        [
            72  # | fannkuchredux-1, 2020-08-07 10:15, php
            , 201  # | fannkuchredux-1, 2020-09-03 13:38, php
            , 218  # | fannkuchredux-1, 2020-09-04 10:45, php
            , 221  # | fannkuchredux - 1 | | 2020 - 09 - 05 02:13: 04.699473 | php
        ],
        # php 8
        [
            70  # fannkuchredux-1, 2020-08-07 10:13:01 php8      |  15 |  178.13
            , 199  # fannkuchredux-1, 2020-09-03 13:38:36 php8      |  15 |  187.91
        ],
        # hhvm
        [
            71  # fannkuchredux-1 2020-08-07 10:14, hhvm
            , 200  # fannkuchredux-1 2020-09-03 13:38, hhvm
        ],
        # jphp
        [
            73  # fannkuchredux-1, 2020-08-07 20:18, jphp
            , 214  # fannkuchredux-1, 2020-09-04 02:33, jphp
        ],
        # graalphp native
        [
            100  # fannkuchredux-1, 2020-08-30 21:14, graalphp-native
            , 102  # fannkuchredux-1, 2020-08-30 21:39, graalphp-native
            , 104  # fannkuchredux-1, 2020-08-30 22:03, graalphp-native
            , 106  # fannkuchredux-1, 2020-08-30 22:28, graalphp-native
            , 108  # fannkuchredux-1, 2020-08-30 22:52, graalphp-native
            , 110  # fannkuchredux-1, 2020-08-30 23:16, graalphp-native
            , 112  # fannkuchredux-1, 2020-08-30 23:40, graalphp-native
            , 114  # fannkuchredux-1, 2020-08-31 00:04, graalphp-native
            , 116  # fannkuchredux-1, 2020-08-31 00:28, graalphp-native
            , 187  # fannkuchredux-1, 2020-09-02 00:16, graalphp-native
        ],
        # graalphp
        [
            99  # fannkuchredux-1, 2020-08-30 21:14, graalphp
            , 101  # fannkuchredux-1, 2020-08-30 21:39, graalphp
            , 103  # fannkuchredux-1, 2020-08-30 22:03, graalphp
            , 105  # fannkuchredux-1, 2020-08-30 22:28, graalphp
            , 107  # fannkuchredux-1, 2020-08-30 22:52, graalphp
            , 109  # fannkuchredux-1, 2020-08-30 23:16, graalphp
            , 111  # fannkuchredux-1, 2020-08-30 23:40, graalphp
            , 113  # fannkuchredux-1, 2020-08-31 00:04, graalphp
            , 115  # fannkuchredux-1, 2020-08-31 00:28, graalphp
            , 186  # fannkuchredux-1, 2020-09-02 00:16, graalphp
        ]
    ]

    # todo: run php again, we currently have 23 samples
    export_to_csv_nested(ids, warmup=5,
                         file_prefix='fannkuchredux', sort=True, limit=True)


if __name__ == '__main__':
    binary_trees()
    spectralnorm()
    fannkuchredux()
