from bench_db import get_timings_by_id
from bench_graphs import do_warmup_plot

num_iter = 7
color_copy_by_ref = 'green'
name = 'native-'


def warmup_all_plots():
    warmup_plot_fannkuch()
    warmup_plot_spectralnorm()
    warmup_plot_bintree()


def warmup_plot_fannkuch():
    ids = [
         100  # fannkuchredux-1, 2020-08-30 21:14, graalphp-native
        , 102  # fannkuchredux-1, 2020-08-30 21:39, graalphp-native
        , 104  # fannkuchredux-1, 2020-08-30 22:03, graalphp-native
        , 106  # fannkuchredux-1, 2020-08-30 22:28, graalphp-native
        , 108  # fannkuchredux-1, 2020-08-30 22:52, graalphp-native
        , 110  # fannkuchredux-1, 2020-08-30 23:16, graalphp-native
        , 112  # fannkuchredux-1, 2020-08-30 23:40, graalphp-native
        , 114  # fannkuchredux-1, 2020-08-31 00:04, graalphp-native
        , 116  # fannkuchredux-1, 2020-08-31 00:28, graalphp-native
    ]
    runs = [get_timings_by_id(i, warmup=0) for i in ids]
    do_warmup_plot('fannkuchredux \ncopy-by-val', runs, num_iter=num_iter, subtitle='',
                   file_prefix=name)
    pass


def warmup_plot_spectralnorm():
    ids_by_val = [
         118  # spectralnorm-by-val,  2020-08-31 00:31:52, graalphp-native
        , 122  # spectralnorm-by-val,  2020-08-31 00:37:51, graalphp-native
        , 126  # spectralnorm-by-val,  2020-08-31 00:43:51, graalphp-native
        , 130  # spectralnorm-by-val,  2020-08-31 00:49:50, graalphp-native
        , 134  # spectralnorm-by-val,  2020-08-31 00:55:49, graalphp-native
        , 138  # spectralnorm-by-val,  2020-08-31 01:01:48, graalphp-native
        , 142  # spectralnorm-by-val,  2020-08-31 01:07:47, graalphp-native
        , 146  # spectralnorm-by-val,  2020-08-31 01:13:47, graalphp-native
        , 150  # spectralnorm-by-val,  2020-08-31 01:19:46, graalphp-native
    ]

    runs = [get_timings_by_id(i, warmup=0) for i in ids_by_val]
    do_warmup_plot('spectralnorm \ncopy-by-val', runs, num_iter=num_iter,
                   file_prefix=name)

    ids_by_ref = [
        120  # spectralnorm-by-ref 2020-08-31 00:34:57, graalphp-native
        , 124  # spectralnorm-by-ref 2020-08-31 00:40:56, graalphp-native
        , 128  # spectralnorm-by-ref 2020-08-31 00:46:55, graalphp-native
        , 132  # spectralnorm-by-ref 2020-08-31 00:52:54, graalphp-native
        , 136  # spectralnorm-by-ref 2020-08-31 00:58:54, graalphp-native
        , 140  # spectralnorm-by-ref 2020-08-31 01:04:53, graalphp-native
        , 144  # spectralnorm-by-ref 2020-08-31 01:10:52, graalphp-native
        , 148  # spectralnorm-by-ref 2020-08-31 01:16:51, graalphp-native
        , 152  # spectralnorm-by-ref 2020-08-31 01:22:50, graalphp-native
    ]

    runs = [get_timings_by_id(i, warmup=0) for i in ids_by_ref]
    do_warmup_plot('spectralnorm \ncopy-by-ref', runs, num_iter=num_iter,
                   color=color_copy_by_ref,
                   file_prefix=name)
    pass


def warmup_plot_bintree():
    ids_by_val = [
          156  # binary-trees-by-val, 2020-09-01 01:57:02, graalphp-native
        , 160  # binary-trees-by-val, 2020-09-01 07:20:34, graalphp-native
        , 164  # binary-trees-by-val, 2020-09-01 12:48:38, graalphp-native
    ]

    runs = [get_timings_by_id(i, warmup=0) for i in ids_by_val]
    do_warmup_plot('binary-trees \ncopy-by-val', runs, num_iter=num_iter,
                   file_prefix=name)


    ids_by_ref = [
          169  # binary-trees-by-ref, 2020-09-01 14:40:30, graalphp-native
        , 171  # binary-trees-by-ref, 2020-09-01 14:48:10, graalphp-native
        , 173  # binary-trees-by-ref, 2020-09-01 14:55:38, graalphp-native
        , 175  # binary-trees-by-ref, 2020-09-01 15:03:06, graalphp-native
        , 177  # binary-trees-by-ref, 2020-09-01 15:10:40, graalphp-native
        , 179  # binary-trees-by-ref, 2020-09-01 15:18:15, graalphp-native
        , 181  # binary-trees-by-ref, 2020-09-01 15:25:48, graalphp-native
        , 183  # binary-trees-by-ref, 2020-09-01 15:33:23, graalphp-native
        , 185  # binary-trees-by-ref, 2020-09-01 15:40:56, graalphp-native
    ]
    runs = [get_timings_by_id(i, warmup=0) for i in ids_by_ref]
    do_warmup_plot('binary-trees \ncopy-by-ref', runs, num_iter=num_iter,
                   color=color_copy_by_ref,
                   file_prefix=name)
    pass


if __name__ == '__main__':
    warmup_plot_fannkuch()
    warmup_plot_spectralnorm()
    warmup_plot_bintree()