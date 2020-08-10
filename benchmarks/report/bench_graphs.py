import datetime
import statistics

from bench_db import export_to_csv, get_timings_by_id


def plot_speedup_box(title, save_name, php_val, gphp_val, gphp_native_val, warmup_thres=0):
    php_mean = statistics.mean(php_val)
    graalphp_val = gphp_val / php_mean
    graalphp_native_val = gphp_native_val / php_mean

    php_val = php_val / php_mean

    labels = ['php', 'graalphp', 'graalphp-native']
    times = [1 / php_val, 1 / graalphp_val, 1 / graalphp_native_val]
    vals = [php_val, gphp_val, gphp_native_val]

    fig = plt.figure()
    ax = fig.gca()
    boxplot = ax.boxplot(times, vert=True, patch_artist=False)

    ax.set_ylabel('Speedup After Warmup')
    xticklabels = []
    for i, l in enumerate(labels):
        xticklabels.append((l, vals[i]))
    ax.set_xticklabels(['%s\n$n$=%d' % (label, len(v)) for label, v in xticklabels])
    ax.set_title(title)
    ax.yaxis.grid(True)
    ax.set_axisbelow(True)
    plt.savefig(save_name)
    plt.show()
    pass


def plot_speedup(title, save_name, php_val, gphp_val, gphp_native_val, warmup_thres=0):
    php_mean = statistics.mean(php_val)

    graalphp_val = gphp_val / php_mean
    graalphp_native_val = gphp_native_val / php_mean

    php_val = php_val / php_mean

    php_mean = statistics.mean(php_val)
    graalphp_mean = statistics.mean(graalphp_val)
    graalphp_native_mean = statistics.mean(graalphp_native_val)

    vals = [php_val, graalphp_val, graalphp_native_val]
    speedup = [php_mean, php_mean / graalphp_mean, php_mean / graalphp_native_mean]

    # Calculate the standard deviation
    php_std = statistics.stdev(php_val)
    graalphp_std = statistics.stdev(graalphp_val)
    graalphp_nat_std = statistics.stdev(graalphp_native_val)

    # Define labels, positions, bar heights and error bar heights
    labels = ['php', 'graalphp', 'graalphp-native']
    x_pos = np.arange(len(labels))
    CTEs = speedup
    # CTEs = [fk_php_mean, fk_graalphp_mean, fk_graalphp_nat_mean]
    error = [php_std, graalphp_std, graalphp_nat_std]

    # Build the plot
    fig, ax = plt.subplots()
    ax.bar(x_pos, CTEs,
           yerr=error,
           align='center',
           alpha=0.5,
           ecolor='black',
           capsize=10)
    ax.set_ylabel('Speedup After Warmup')
    ax.set_xticks(x_pos)
    # ax.set_xticklabels(labels)
    ax.set_title(title)

    xticklabels = []
    for i, l in enumerate(labels):
        xticklabels.append((l, vals[i]))
    ax.set_xticklabels(['%s\n$n$=%d' % (label, len(v)) for label, v in xticklabels])
    ax.yaxis.grid(True)

    # Save the figure and show
    plt.tight_layout()
    plt.savefig(save_name)
    plt.show()


import numpy as np
import seaborn as sns
import matplotlib.patches as mpatches
import matplotlib.pyplot as plt


def binary_trees_report_plot():
    vals_php = get_timings_by_id(78)
    vals_php8 = get_timings_by_id(77)

    vals_php_ref = get_timings_by_id(75)
    vals_php8_ref = get_timings_by_id(74)

    vals_gphp_ref = get_timings_by_id(91)
    vals_gphp_nat_ref = get_timings_by_id(92)

    vals_gphp_val = get_timings_by_id(93)
    vals_gphp_nat_val = get_timings_by_id(94)

    vals_jphp = get_timings_by_id(79)
    vals_hhvm = get_timings_by_id(76)

    impl_txt = (
        # by val
        'PHP 7 \n(baseline)',
        'PHP 8 Alpha',
        'JPHP',
        'HHVM',
        'graalphp-native',
        'graalphp',
        # by ref
        'PHP 7',
        'PHP 8 Alpha',
        'graalphp-native',
        'graalphp'
    )

    vals = [
        # val
        vals_php
        , vals_php8
        , vals_jphp
        , vals_hhvm
        , vals_gphp_val
        , vals_gphp_nat_val
        # ref
        , vals_php_ref
        , vals_php8_ref
        , vals_gphp_nat_ref
        , vals_gphp_ref
    ]

    avg_baseline = statistics.mean(vals_php)
    avgs = [statistics.mean(v) for v in vals]

    def do_variance(vals, baseline):
        return statistics.variance([v / baseline for v in vals])

    def do_speedup(val, baseline):
        return baseline / val

    speedups = [do_speedup(v, avg_baseline) for v in avgs]
    variance = [do_variance(v, avg_baseline) for v in vals]

    plt.rcParams.update({
        "text.usetex": True,
        "font.family": "serif",
        "font.serif": ["Palatino"],
    })

    plt.rcParams.update({
        "text.usetex": True,
        "font.family": "serif",
    })

    title = 'Binary-Trees Benchmark'
    xlabel = 'Speedup (larger is better)'
    ylabel = 'Implementation'

    impl_indices = np.arange(len(impl_txt))

    print(speedups)
    print(variance)

    color_by_val = 'blue'
    color_by_ref = 'green'

    fig, ax = plt.subplots()
    ax.barh(impl_indices, speedups,
            # xerr=variance,
            align='center', alpha=.9
            , color=color_by_val)
    plt.yticks(impl_indices, impl_txt)
    plt.xlabel(xlabel)
    plt.title(title)
    sns.despine()

    ax.get_children()[9].set_color(color_by_ref)
    ax.get_children()[8].set_color(color_by_ref)
    ax.get_children()[7].set_color(color_by_ref)
    ax.get_children()[6].set_color(color_by_ref)

    for i, v in enumerate(speedups):
        ax.text(v + .2, i, '{:.2f}'.format(v), va='center', color='gray')

    patch_by_val = mpatches.Patch(color=color_by_val, label='copy by value (default)')
    patch_by_ref = mpatches.Patch(color=color_by_ref, label='copy by reference (explicit)')

    plt.legend(handles=[patch_by_ref, patch_by_val], loc='lower right')
    plt.draw()
    plt.tight_layout()

    date = str(datetime.datetime.now()).replace(':', '-').replace(' ', '-')
    plt.savefig("binary-trees-" + date + '.svg')
    plt.show()


def spectralnorm_report_plot():
    php_baseline = 82

    ids = [
        # by val
        php_baseline,  # php val
        81,  # php 8 val
        83,  # jpnp val
        80,  # hhvm
        96,  # gpnpn, val
        95,  # gpnp, val
        # by ref
        87,  # PHP ref
        86,  # PHP 8 ref
        88,  # jphp ref
        98,  # gphp native
        97  # gpnp
    ]
    impl_txt = (
        # by val
        'PHP 7 \n(baseline)',
        'PHP 8 Alpha',
        'JPHP',
        'HHVM',
        'graalphp-native',
        'graalphp',
        # by ref
        'PHP 7',
        'PHP 8 Alpha',
        'JPHP',
        'graalphp-native',
        'graalphp'
    )

    vals = [get_timings_by_id(i) for i in ids]
    print(export_to_csv(ids, write_file=False))
    avg_baseline = statistics.mean(get_timings_by_id(php_baseline))

    avgs = [statistics.mean(v) for v in vals]

    def do_variance(vals, baseline):
        return statistics.variance([v / baseline for v in vals])

    def do_speedup(val, baseline):
        return baseline / val

    speedups = [do_speedup(v, avg_baseline) for v in avgs]
    variance = [do_variance(v, avg_baseline) for v in vals]

    plt.rcParams.update({
        "text.usetex": True,
        "font.family": "serif",
        "font.serif": ["Palatino"],
    })

    title = 'Spectralnorm Benchmark'
    xlabel = 'Speedup (larger is better)'

    impl_indices = np.arange(len(impl_txt))

    print(speedups)
    print(variance)

    color_by_val = 'blue'
    color_by_ref = 'green'

    fig, ax = plt.subplots()
    ax.barh(impl_indices, speedups,
            xerr=variance,
            align='center', alpha=.9
            , color=color_by_val)
    plt.yticks(impl_indices, impl_txt)
    plt.xlabel(xlabel)
    plt.title(title)
    sns.despine()

    ax.get_children()[10].set_color(color_by_ref)
    ax.get_children()[9].set_color(color_by_ref)
    ax.get_children()[8].set_color(color_by_ref)
    ax.get_children()[7].set_color(color_by_ref)
    ax.get_children()[6].set_color(color_by_ref)

    for i, v in enumerate(speedups):
        ax.text(v + .2, i, '{:.2f}'.format(v), va='center', color='gray')

    patch_by_val = mpatches.Patch(color=color_by_val, label='copy by value (default)')
    patch_by_ref = mpatches.Patch(color=color_by_ref, label='copy by reference (explicit)')

    plt.legend(handles=[patch_by_ref, patch_by_val], loc='lower right')
    plt.draw()
    plt.tight_layout()

    date = str(datetime.datetime.now()).replace(':', '-').replace(' ', '-')
    plt.savefig("spectralnorm-" + date + '.svg')

    print(plt.rcParams)
    plt.show()


def fannkuchredux():
    php_baseline = 72

    ids = [
        php_baseline,
        70,  # php 8
        73,  # jphp
        71,  # hhvm
        90,  # gphp native
        89,  # gphp
    ]
    impl_txt = (
        # by val
        'PHP 7 \n(baseline)',
        'PHP 8 Alpha',
        'JPHP',
        'HHVM',
        'graalphp-native',
        'graalphp',
    )

    vals = [get_timings_by_id(i) for i in ids]
    print(export_to_csv(ids, write_file=False))

    avg_baseline = statistics.mean(get_timings_by_id(php_baseline))

    avgs = [statistics.mean(v) for v in vals]

    def do_variance(vals, baseline):
        return statistics.variance([v / baseline for v in vals])

    def do_speedup(val, baseline):
        return baseline / val

    speedups = [do_speedup(v, avg_baseline) for v in avgs]
    variance = [do_variance(v, avg_baseline) for v in vals]

    plt.rcParams.update({
        "text.usetex": True,
        "font.family": "serif",
        "font.serif": ["Palatino"],
    })

    title = 'Fannkuchredux Benchmark'
    xlabel = 'Speedup (larger is better)'

    impl_indices = np.arange(len(impl_txt))

    print(speedups)
    print(variance)

    color_by_val = 'blue'

    fig, ax = plt.subplots(figsize=(6, 3))
    ax.barh(impl_indices, speedups,
            xerr=variance,
            align='center', alpha=.9
            , color=color_by_val)

    plt.yticks(impl_indices, impl_txt)
    plt.xlabel(xlabel)
    plt.title(title)
    sns.despine()

    for i, v in enumerate(speedups):
        ax.text(v + .2, i, '{:.2f}'.format(v), va='center', color='gray')

    plt.draw()
    plt.tight_layout()

    date = str(datetime.datetime.now()).replace(':', '-').replace(' ', '-')
    plt.savefig("fannkuchredux-" + date + '.svg')

    plt.show()


if __name__ == '__main__':
    binary_trees_report_plot()
