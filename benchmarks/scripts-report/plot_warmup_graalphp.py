from bench_db import get_timings_by_id
from bench_graphs import do_warmup_plot

num_iter = 7
color_copy_by_ref = 'green'


def warmup_all_plots():
    warmup_plot_fannkuch()
    warmup_plot_spectralnorm()
    # warmup_plot_bintree()


def warmup_plot_fannkuch():
    ids = [
        99  # 2020-08-30,  graalphp   , fannkuchredux-1
        , 101  # 2020-08-30,  graalphp, fannkuchredux-1
        , 103  # 2020-08-30,  graalphp, fannkuchredux-1
        , 105  # 2020-08-30,  graalphp, fannkuchredux-1
        , 107  # 2020-08-30,  graalphp, fannkuchredux-1
        , 109  # 2020-08-30,  graalphp, fannkuchredux-1
        , 111  # 2020-08-30,  graalphp, fannkuchredux-1
        , 113  # 2020-08-31,  graalphp, fannkuchredux-1
        , 115  # 2020-08-31,  graalphp, fannkuchredux-1
    ]
    runs = [get_timings_by_id(i, warmup=0) for i in ids]
    do_warmup_plot('fannkuchredux \ncopy-by-val', runs, num_iter=num_iter, subtitle='',
                   file_prefix='graalphp-')
    pass


def warmup_plot_spectralnorm():
    ids_by_val = [
        117  # spectralnorm-by-val, 2020-08-31 00:31,  graalphp
        , 121  # spectralnorm-by-val, 2020-08-31 00:37,  graalphp
        , 125  # spectralnorm-by-val, 2020-08-31 00:43,  graalphp
        , 129  # spectralnorm-by-val, 2020-08-31 00:49,  graalphp
        , 133  # spectralnorm-by-val, 2020-08-31 00:55,  graalphp
        , 137  # spectralnorm-by-val, 2020-08-31 01:01,  graalphp
        , 141  # spectralnorm-by-val, 2020-08-31 01:07,  graalphp
        , 145  # spectralnorm-by-val, 2020-08-31 01:13,  graalphp
        , 149  # spectralnorm-by-val, 2020-08-31 01:19,  graalphp
    ]

    runs = [get_timings_by_id(i, warmup=0) for i in ids_by_val]
    do_warmup_plot('spectralnorm \ncopy-by-val', runs, num_iter=num_iter,
                   file_prefix='graalphp-')

    ids_by_ref = [
        119  # spectralnorm-by-ref, 2020-08-31 00:34,  graalphp
        , 123  # spectralnorm-by-ref, 2020-08-31 00:40,  graalphp
        , 127  # spectralnorm-by-ref, 2020-08-31 00:46,  graalphp
        , 131  # spectralnorm-by-ref, 2020-08-31 00:52,  graalphp
        , 135  # spectralnorm-by-ref, 2020-08-31 00:58,  graalphp
        , 139  # spectralnorm-by-ref, 2020-08-31 01:04,  graalphp
        , 143  # spectralnorm-by-ref, 2020-08-31 01:10,  graalphp
        , 147  # spectralnorm-by-ref, 2020-08-31 01:16,  graalphp
        , 151  # spectralnorm-by-ref, 2020-08-31 01:22,  graalphp
    ]

    runs = [get_timings_by_id(i, warmup=0) for i in ids_by_ref]
    do_warmup_plot('spectralnorm \ncopy-by-ref', runs, num_iter=num_iter,
                   color=color_copy_by_ref,
                   file_prefix='graalphp-')
    pass


def warmup_plot_bintree():
    runs = []

    runs.append(get_timings_by_id(89, warmup=0))
    do_warmup_plot('binary-trees \ncopy-by-val', runs, num_iter=num_iter,
                   file_prefix='graalphp-')

    do_warmup_plot('binary-trees \ncopy-by-ref', runs, num_iter=num_iter,
                   color=color_copy_by_ref,
                   file_prefix='graalphp-')
    pass


if __name__ == '__main__':
    warmup_plot_fannkuch()
    warmup_plot_spectralnorm()