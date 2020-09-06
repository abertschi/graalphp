import datetime
import os
import statistics
from time import strftime, gmtime

import pandas as pd
from bench_db import export_to_csv, get_timings_by_id, export_to_csv_nested
# from datetime import datetime
from bench_export_csv import bintree_ids_php_by_val, bintree_ids_php8_by_val, bintree_ids_jphp_by_val, bintree_ids_hhvm, \
    bintree_ids_graalphp_native_by_val, bintree_ids_graalphp_by_val, bintree_ids_php_by_ref, bintree_ids_php8_by_ref, \
    bintree_ids_jphp_by_ref, bintree_ids_graalphp_native_by_ref, bintree_ids_graalphp_by_ref, fannkuch_ids_php_by_val, \
    fannkuch_ids_php8_by_val, fannkuch_ids_jphp_by_val, fannkuch_ids_hhvm, fannkuch_ids_graalphp_native_by_val, \
    fannkuch_ids_graalphp_by_val, spectral_ids_php_by_ref, spectral_ids_php8_by_ref, spectral_ids_jphp_by_ref, \
    spectral_ids_graalphp_native_by_ref, spectral_ids_graalphp_by_ref, spectral_ids_graalphp_by_val, \
    spectral_ids_graalphp_native_by_val, spectral_ids_hhvm, spectral_ids_jphp_by_val, spectral_ids_php8_by_val, \
    spectral_ids_php_by_val


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
    ids = [
        # by val
        bintree_ids_php_by_val  # php 7
        , bintree_ids_php8_by_val  # php 8
        , bintree_ids_jphp_by_val  # jphp
        , bintree_ids_hhvm  # hhvm
        , bintree_ids_graalphp_native_by_val  # gphp native
        , bintree_ids_graalphp_by_val  # gphp
        # by ref
        , bintree_ids_php_by_ref
        , bintree_ids_php8_by_ref
        , bintree_ids_jphp_by_ref
        , bintree_ids_graalphp_native_by_ref
        , bintree_ids_graalphp_by_ref
    ]
    vals = []
    for ids_bench in ids:
        val_ids = []
        for i in ids_bench:
            val_ids += get_timings_by_id(i, warmup=5)
        vals.append(val_ids)

    impl_txt = (
        # by val
        'PHP 7 \n(baseline)',
        'PHP 8 Alpha',
        'JPHP',
        'HHVM',
        '\\textbf{graalphp-native}',
        '\\textbf{graalphp}',
        # by ref
        'PHP 7',
        'PHP 8 Alpha',
        'JPHP',
        '\\textbf{graalphp-native}',
        '\\textbf{graalphp}'
    )

    vals_php = vals[0]
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

    title = 'Binary-Trees'
    xlabel = 'Speedup (larger is better)'
    ylabel = 'Implementation'

    impl_indices = np.arange(len(impl_txt))

    print(impl_indices)
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

    ax.get_children()[10].set_color(color_by_ref)
    ax.get_children()[9].set_color(color_by_ref)
    ax.get_children()[8].set_color(color_by_ref)
    ax.get_children()[7].set_color(color_by_ref)
    ax.get_children()[6].set_color(color_by_ref)

    for i, v in enumerate(speedups):
        ax.text(v + .2, i, '{:.2f}'.format(v), va='center', color='gray')

    patch_by_val = mpatches.Patch(color=color_by_val, label='copy-by-value (default)')
    patch_by_ref = mpatches.Patch(color=color_by_ref, label='copy-by-reference (explicit)')

    plt.legend(handles=[patch_by_ref, patch_by_val], loc='lower right')
    plt.draw()
    plt.tight_layout()

    date = str(datetime.datetime.now()).replace(':', '-').replace(' ', '-')
    plt.savefig("binary-trees-" + date + '.svg')
    plt.show()


def spectralnorm_report_plot():
    impl_txt = (
        # by val
        'PHP 7 \n(baseline)',
        'PHP 8 Alpha',
        'JPHP',
        'HHVM',
        '\\textbf{graalphp-native}',
        '\\textbf{graalphp}',
        # by ref
        'PHP 7',
        'PHP 8 Alpha',
        'JPHP',
        '\\textbf{graalphp-native}',
        '\\textbf{graalphp}'
    )

    ids = [
        # by val
          spectral_ids_php_by_val  # php 7
        , spectral_ids_php8_by_val  # php 8
        , spectral_ids_jphp_by_val  # jphp
        , spectral_ids_hhvm  # hhvm
        , spectral_ids_graalphp_native_by_val  # gphp native
        , spectral_ids_graalphp_by_val  # gphp
        # by ref
        , spectral_ids_php_by_ref
        , spectral_ids_php8_by_ref
        , spectral_ids_jphp_by_ref
        , spectral_ids_graalphp_native_by_ref
        , spectral_ids_graalphp_by_ref
    ]
    vals = []
    for ids_bench in ids:
        val_ids = []
        for i in ids_bench:
            val_ids += get_timings_by_id(i, warmup=5)
        vals.append(val_ids)

    print(export_to_csv_nested(ids, write_file=False))
    avg_baseline = statistics.mean(vals[0])

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

    title = 'Spectralnorm'
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

    ax.get_children()[11].set_color(color_by_ref)
    ax.get_children()[10].set_color(color_by_ref)
    ax.get_children()[9].set_color(color_by_ref)
    ax.get_children()[8].set_color(color_by_ref)
    ax.get_children()[7].set_color(color_by_ref)
    # ax.get_children()[6].set_color(color_by_ref)
    for i, v in enumerate(speedups):
        ax.text(v + .2, i, '{:.2f}'.format(v), va='center', color='gray')

    patch_by_val = mpatches.Patch(color=color_by_val, label='copy-by-value (default)')
    patch_by_ref = mpatches.Patch(color=color_by_ref, label='copy-by-reference (explicit)')

    plt.legend(handles=[patch_by_ref, patch_by_val], loc='lower right')
    plt.draw()
    plt.tight_layout()

    date = str(datetime.datetime.now()).replace(':', '-').replace(' ', '-')
    plt.savefig("spectralnorm-" + date + '.svg')

    print(plt.rcParams)
    plt.show()


def fannkuchredux():
    ids = [
        # by val
          fannkuch_ids_php_by_val  # php 7
        , fannkuch_ids_php8_by_val  # php 8
        , fannkuch_ids_jphp_by_val  # jphp
        , fannkuch_ids_hhvm  # hhvm
        , fannkuch_ids_graalphp_native_by_val  # gphp native
        , fannkuch_ids_graalphp_by_val  # gphp
    ]
    vals = []
    for ids_bench in ids:
        val_ids = []
        for i in ids_bench:
            val_ids += get_timings_by_id(i, warmup=5)
        vals.append(val_ids)

    impl_txt = (
        # by val
        'PHP 7 \n(baseline)',
        'PHP 8 Alpha',
        'JPHP',
        'HHVM',
        '\\textbf{graalphp-native}',
        '\\textbf{graalphp}',
    )


    print(export_to_csv_nested(ids, write_file=False))

    avg_baseline = statistics.mean(vals[0])

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

    title = 'Fannkuchredux'
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

    patch_by_val = mpatches.Patch(color=color_by_val, label='copy-by-value (default)')

    plt.legend(handles=[patch_by_val], loc='lower right')

    plt.draw()
    plt.tight_layout()

    date = str(datetime.datetime.now()).replace(':', '-').replace(' ', '-')
    plt.savefig("fannkuchredux-" + date + '.svg')

    plt.show()



def do_warmup_plot(name, runs, num_iter, subtitle='', color='blue', file_prefix=''):
    values = []
    groups = []

    N = len(runs)
    for i in range(num_iter):
        for r in runs:
            print(r)
            values.append(r[i])
            groups.append(i + 1)

    df = pd.DataFrame({'value': values, 'group': groups})
    print(df)

    plt.rcParams.update({
        "text.usetex": True,
        "font.family": "serif",
        "font.serif": ["Palatino"],
    })
    sns.set_color_codes()
    sns.despine()

    ax = df.boxplot(column='value', by='group', showfliers=True,
                    positions=range(df.group.unique().shape[0]), color="black")

    g = sns.pointplot(x='group', y='value', data=df.groupby('group', as_index=False).mean(), ax=ax, color=color,
                      linestyles='--', scale=0.5)

    plt.suptitle(subtitle, fontsize=15)
    plt.ylabel("execution time (s)")
    plt.xlabel("iteration")
    plt.title('')

    vals = df[["value"]].to_numpy()
    # plt.yticks(np.arange(min(vals) - 10, max(vals) + 10, 5))
    fig = plt.gcf()
    fig.set_size_inches(7, 2)

    ax2 = ax.twinx()
    ax2.set_ylabel('{} (N={})'.format(name, N)
                   , rotation=270, labelpad=30)  # fontsize=12)
    ax2.set_yticklabels([])
    ax2.set_yticks([])

    date = str(datetime.datetime.now()).replace(':', '-').replace(' ', '-').replace('.', '-')
    sns.despine(bottom=True, left=True)
    os.makedirs('render', exist_ok=True)
    plt.savefig("render/warmum-" + file_prefix + name.split(' ')[0] + date + '.svg')
    plt.show()

    pass


if __name__ == '__main__':
    binary_trees_report_plot()
    fannkuchredux()
    spectralnorm_report_plot()


