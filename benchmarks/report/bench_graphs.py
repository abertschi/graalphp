import statistics

import matplotlib.pyplot as plt
import numpy as np

import bench


def get_timings(path):
    bm = bench.Bench()
    vals = bm.parse_values(path)
    timings = bm.extract_timings(vals, None)
    bm.print_statistics(timings)
    return timings


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


def fannkuch_plot():
    prefix = 'saved-measurements/20-07-20/'
    php = '2020-07-20T02:38:05.827873-fannkuch-php.txt'
    graalphp = '2020-07-20T02:38:05.827873-fannkuch-graalphp.txt'
    graalphp_native = '2020-07-20T02:38:05.827873-fannkuch-graalphp-native.txt'
    warmup_thres = 10

    php_val = get_timings(os.path.join(prefix, php))

    graalphp_val = get_timings(os.path.join(prefix, graalphp))[warmup_thres:]
    graalphp_native_val = get_timings(os.path.join(prefix, graalphp_native))[warmup_thres:]
    plot_speedup("Fannkuch Benchmark", 'fannkuch.png',
                 php_val, graalphp_val, graalphp_native_val)

    plot_speedup_box("Fannkuch Benchmark", 'fannkuch-box.png',
                     php_val, graalphp_val, graalphp_native_val)


def binary_trees_val_plot():
    folder = 'saved-measurements/20-07-31-graal-20.0.0-binary-trees/'

    prefix = folder + '2020-07-31T01:08:00.473402-binary-trees-binarytrees.php-3.'

    php = prefix + 'php-php.txt'
    graalphp = prefix + 'graalphp-graalphp.txt'
    graalphp_native = prefix + 'graalphp-graalphp-native.txt'
    warmup_thres = 5

    php_val = get_timings(php)
    graalphp_val = get_timings(graalphp)[warmup_thres:]
    graalphp_native_val = get_timings(graalphp_native)[warmup_thres:]
    plot_speedup("Binary Tree Benhmark (copy by value)", 'binary-trees-copy-by-value.png',
                 php_val, graalphp_val, graalphp_native_val)

    plot_speedup_box("Binary Tree Benhmark (copy by value)", 'binary-trees-copy-by-value-box.png',
                     php_val, graalphp_val, graalphp_native_val)


def binary_trees_ref_plot():
    prefix = 'saved-measurements/20-07-20/'
    php = '2020-07-19T22:03:31.585475-binary-trees-php.txt'
    graalphp = '2020-07-19T22:03:31.585475-binary-trees-graalphp.txt'
    graalphp_native = '2020-07-19T22:03:31.585475-binary-trees-graalphp-native.txt'
    warmup_thres = 10

    php_val = get_timings(os.path.join(prefix, php))
    graalphp_val = get_timings(os.path.join(prefix, graalphp))[warmup_thres:]
    graalphp_native_val = get_timings(os.path.join(prefix, graalphp_native))[warmup_thres:]
    plot_speedup("Binary Tree Benhmark (copy by ref)", 'binary-trees-copy-by-ref.png',
                 php_val, graalphp_val, graalphp_native_val)

    plot_speedup_box("Binary Tree Benhmark (copy by ref)", 'binary-trees-copy-by-ref-box.png',
                     php_val, graalphp_val, graalphp_native_val)


def spectralnorm_ref_plot():
    prefix = 'saved-measurements/20-07-27/2020-07-26T22:24:05.785564-spectralnorm-'
    php = 'spectralnorm.php-2.php-php.txt'
    graalphp = 'spectralnorm.php-2.graalphp-graalphp.txt'
    graalphp_native = 'spectralnorm.php-2.graalphp-graalphp-native.txt'
    warmup_thres = 10
    php_val = get_timings(prefix + php)
    print(php_val)

    graalphp_val = get_timings(prefix + graalphp)[warmup_thres:]
    graalphp_native_val = get_timings(prefix + graalphp_native)[warmup_thres:]

    plot_speedup('Spectral Norm (Arrays copy by Reference)', 'spectral-norm-ref.png', php_val, graalphp_val,
                 graalphp_native_val)
    plot_speedup_box('Spectral Norm (Arrays copy by Reference)', 'spectral-norm-ref-boxplot.png', php_val, graalphp_val,
                     graalphp_native_val)


def spectralnorm_val_plot():
    prefix = 'saved-measurements/20-07-27/2020-07-26T22:24:05.785564-spectralnorm-'

    # php = 'spectralnorm.php-2-php-unmodified.php-php.txt'
    php = 'spectralnorm.php-2-pass-by-val.php-php.txt'
    graalphp = 'spectralnorm.php-2-pass-by-val.graalphp-graalphp.txt'
    graalphp_native = 'spectralnorm.php-2-pass-by-val.graalphp-graalphp-native.txt'
    warmup_thres = 10

    php_val = get_timings(prefix + php)
    graalphp_val = get_timings(prefix + graalphp)[warmup_thres:]
    graalphp_native_val = get_timings(prefix + graalphp_native)[warmup_thres:]

    plot_speedup('Spectral Norm (Arrays copy by Value)', 'spectral-norm-val.png', php_val, graalphp_val,
                 graalphp_native_val)
    plot_speedup_box('Spectral Norm (Arrays copy by Value)', 'spectral-norm-val-boxplot.png', php_val, graalphp_val,
                     graalphp_native_val)


def spectralnorm_untouched_plot():
    prefix = 'saved-measurements/20-07-27/2020-07-26T22:24:05.785564-spectralnorm-'

    php = 'spectralnorm.php-2-php-unmodified.php-php.txt'
    graalphp = 'spectralnorm.php-2-pass-by-val.graalphp-graalphp.txt'
    graalphp_native = 'spectralnorm.php-2-pass-by-val.graalphp-graalphp-native.txt'
    warmup_thres = 10

    php_val = get_timings(prefix + php)
    graalphp_val = get_timings(prefix + graalphp)[warmup_thres:]
    graalphp_native_val = get_timings(prefix + graalphp_native)[warmup_thres:]

    plot_speedup('Spectral Norm (Global Variables)', 'spectral-norm-glob.png', php_val, graalphp_val,
                 graalphp_native_val)
    plot_speedup_box('Spectral Norm (Global Variables', 'spectral-norm-glob-boxplot.png', php_val, graalphp_val,
                     graalphp_native_val)


if __name__ == '__main__':
    spectralnorm_ref_plot()
