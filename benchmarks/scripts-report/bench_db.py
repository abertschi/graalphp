#!/bin/env python3
import csv
import datetime
import os
import statistics
import sys
from shutil import copyfile

import scipy.stats as st
from pony.orm import *
from prettytable import PrettyTable

db = Database()


class Statistics(db.Entity):
    n = Required(int)
    mean = Optional(float)
    stdev = Optional(float)
    min = Optional(float)
    max = Optional(float)
    variance = Optional(float)
    run = Required("Run")


class Measurement(db.Entity):
    iteration = Required(int)
    timing_ms = Required(float)
    run = Required('Run')


class Run(db.Entity):
    src_file = Optional(str)
    out_file = Optional(str)
    prefix = Optional(str)
    binary = Optional(str)
    command = Optional(str)
    comment = Optional(str)
    date = Required(datetime.datetime)
    commit = Optional(str)
    measurements = Set('Measurement', reverse='run')
    statistics = Optional(Statistics)
    benchmark = Required('Benchmark')

    unused1 = Optional(str)
    unused2 = Optional(str)


class Benchmark(db.Entity):
    name = Required(str)
    type = Optional(str)
    unused1 = Optional(str)
    runs = Set('Run', reverse='benchmark')


FILE_NAME = 'measurements.sqlite'

script_dir = os.path.dirname(os.path.realpath(__file__))
tmp_dir = os.path.join(script_dir, FILE_NAME + '.backups')
os.makedirs(tmp_dir, exist_ok=True)

tmp_dir_copy_from = os.path.join(script_dir, FILE_NAME)
tmp_dir_copy_to = os.path.join(tmp_dir,
                               datetime.datetime.now().isoformat() + '-' + FILE_NAME)

print('backing up database to: ' + tmp_dir_copy_to)
try:
    copyfile(tmp_dir_copy_from, tmp_dir_copy_to)
except Exception as e:
    print(e)

print("loading db: " + os.path.join(script_dir, FILE_NAME))
db.bind(provider='sqlite', filename=FILE_NAME, create_db=True)
db.generate_mapping()


@db_session
def store_measurements(test_name,
                       timings,  # []
                       src_file='',
                       out_file='',
                       prefix='',
                       binary='',
                       command='',
                       comment='',
                       date=None,
                       commit='',
                       binary_version='',
                       confirm_store=False):
    print('storing measurements: testname: {}, timings: {}, '
          'prefix: {}, binary: {}, command: {}, comment: {}, commit: {}, binary_version: {}'
          .format(test_name, timings, prefix, binary, command, comment, commit, binary_version))

    if confirm_store:
        input("Press Enter to continue...")

    name = test_name

    bm = Benchmark.get(name=name)
    if not bm:
        bm = Benchmark(name=name)

    run = Run(src_file=src_file,
              date=date if date else datetime.datetime.now(),
              command=command,
              commit=commit,
              binary=binary,
              prefix=prefix,
              comment=comment,
              out_file=out_file,
              benchmark=bm,
              unused1=binary_version)

    i = 1
    measurements = []
    for mes in timings:
        measurements.append(Measurement(iteration=i, timing_ms=mes, run=run))
        i = i + 1

    run.measurements = measurements

    db.commit()
    print(query_results_with_run_id(run.id))
    pass


@db_session
def query_results_with_prefix(prefix):
    runs = list(select(run for run in Run
                       if run.prefix.startswith(prefix)))
    result = []
    for run in runs:
        measurements = list(select(m for m in Measurement
                                   if m.run.id == run.id).order_by(Measurement.iteration))
        measurements = [m.timing_ms for m in measurements]
        result.append([run.benchmark.name, run.binary, run.command, run.prefix, measurements])

    return result


@db_session
def query_results_with_run_id(id):
    runs = list(select(run for run in Run
                       if run.id == id))
    result = []
    for run in runs:
        measurements = list(select(m for m in Measurement
                                   if m.run.id == run.id).order_by(Measurement.iteration))
        measurements = [m.timing_ms for m in measurements]
        result.append([run.benchmark.name, run.binary, run.command, run.prefix, measurements])

    return result


@db_session
def show_all_dump():
    print('\nBenchmark:')
    Benchmark.select().show(width=1000)
    print('\nRun:')
    Run.select().show(width=1000)
    print('\nMeasurement:')
    Measurement.select().show(width=1000)
    print('\nStatistics:')
    Statistics.select().show(width=1000)
    pass


@db_session
def show_all_curated(warmup=5):
    t = PrettyTable(field_names=['ID',
                                 'Benchmark Name',
                                 'Type',
                                 'Date',
                                 'Binary',
                                 "N",
                                 'Mean(s)',
                                 'Stdev',
                                 'Variance',
                                 'Min',
                                 'Max',
                                 'Warmup Count',
                                 'Comment',
                                 'Unused1',
                                 'Unused2',
                                 'Raw'], float_format='.2')

    bms = select(b for b in Benchmark)
    for bm in list(bms):
        name = bm.name
        type = bm.type

        for run in list(select(r for r in Run
                               if r.benchmark.name == name).order_by(Run.binary, Run.date)):
            date = run.date
            binary = run.binary
            cmd = run.command
            id = run.id
            comment = run.comment
            measurements = select(m for m in Measurement
                                  if m.run.id == run.id).order_by(Measurement.iteration)
            timings = []
            for data in list(measurements):
                timings.append(data.timing_ms / 1000)

            row = [id,
                   name,
                   type,
                   date,
                   binary,
                   len(timings),
                   round(statistics.mean(timings), 2),
                   round(statistics.stdev(timings), 2),
                   round(statistics.variance(timings), 2),
                   round(min(timings), 2),
                   round(max(timings), 2),
                   0,
                   comment[:80],
                   run.unused1[:50],
                   run.unused2[:50],
                   ''
                   ]
            t.add_row(row)
            if warmup != 0 and warmup < len(timings):
                timings = timings[warmup:]
                row2 = [id,
                        name,
                        type,
                        date,
                        binary,
                        len(timings),
                        round(statistics.mean(timings), 2),
                        round(statistics.stdev(timings), 2),
                        round(statistics.variance(timings), 2),
                        round(min(timings), 2),
                        round(max(timings), 2),
                        warmup,
                        comment[:80],
                        run.unused1[:50],
                        run.unused2[:50],
                        ''
                        ]
                t.add_row(row2)
        print(t)
        print('\n')
        t.clear_rows()


# orders by measurement run
@db_session
def get_timings_by_id(id, warmup=5):
    measurements = list(select(m for m in Measurement
                               if m.run.id == id).order_by(Measurement.iteration))
    filtered = measurements[warmup:]
    timings = []
    for m in filtered:
        timings.append(m.timing_ms / 1000)  # vals in secods

    return timings


@db_session
def export_to_csv_nested(nested_ids, warmup=5,
                         export_dir=os.path.dirname(os.path.realpath(__file__)) + '/export',
                         file_prefix='',
                         sort=True,
                         limit=True,
                         write_file=True,
                         confidence=0.95):
    """
    :param nested_ids: nested array, result is grouped by first layer.
                       this allows to aggregate results of same implementation across multiple runs
    """
    validate_nested_ids(nested_ids)
    rows = [
        ["implementation", "benchmark", "type", "n", "warmup", "mean", "median", "min", "max", "stdev", "variance",
         "db-id", "number-of-restarts", 'CI-' + str(confidence)]]

    for ids_to_same_bench in nested_ids:
        filtered = []
        bench_run = None
        for i in ids_to_same_bench:
            r = list(select(r for r in Run if r.id == i))
            if len(r) != 1:
                print('error, ID not unique: ' + i)
                exit(1)
            r = r[0]
            assert (bench_run is None) or (r.benchmark.name == bench_run.benchmark.name)
            bench_run = r

            mes = list(select(m for m in Measurement
                              if m.run.id == r.id).order_by(Measurement.iteration))
            filtered = filtered + mes[warmup:]

        timings = []
        for m in filtered:
            timings.append(m.timing_ms / 1000)  # vals in secods
        print('samples for ids: {}: {}', ids_to_same_bench, filtered)

        def f(val):
            # return '{:.2E}'.format(val)
            if val < 0.01:
                return '$<$ 0.01'
            return '{:.2f}'.format(val)

        def format_list(list):
            res = ''
            for l in list:
                res += str(l) + ';'
            return res

        def format_interval(i):
            if '{:.2f}'.format(i[0]) == '{:.2f}'.format(i[1]):
                return ('$\sim\ ${:.2f}'.format(i[0]), '$\sim\ ${:.2f}'.format(i[0]))
            else:
                return (f(interval[0]), f(interval[1]))

        interval = st.norm.interval(confidence, loc=statistics.mean(timings), scale=st.sem(timings))
        interval = format_interval(interval)

        row = [
            bench_run.binary,
            bench_run.benchmark.name,
            '',
            len(timings),
            warmup,
            f(statistics.mean(timings)),
            f(statistics.median(timings)),
            f(min(timings)),
            f(max(timings)),
            f(statistics.stdev(timings)),
            f(statistics.variance(timings)),
            format_list(ids_to_same_bench),
            len(ids_to_same_bench),
            '[{}; {}]'.format(interval[0], interval[1])
        ]
        rows.append(row)

    if sort:
        sorting = rows[1:]
        rows_sorted = sorted(sorting, key=lambda x: float(x[5]), reverse=False)
        rows = [rows[0]] + rows_sorted

    if file_prefix:
        file_prefix += '-'

    file_name = os.path.join(export_dir, "export-" + file_prefix + str(datetime.datetime.now()) + ".csv.txt")
    file_name = file_name.replace(' ', '-').replace(':', '-')
    os.makedirs(export_dir, exist_ok=True)
    if write_file:
        print('writing file' + file_name)
        with open(file_name, 'w', newline='\n') as file:
            writer = csv.writer(file)
            writer.writerows(rows)


@db_session
def export_to_csv(ids=[], warmup=5,
                  export_dir=os.path.dirname(os.path.realpath(__file__)),
                  file_prefix='',
                  sort=True,
                  limit=True,
                  write_file=True):
    rows = [
        ["implementation", "benchmark", "n", "warmup", "mean", "median", "min", "max", "stdev", "variance", "db-id"]]

    for i in ids:
        r = list(select(r for r in Run if r.id == i))
        if len(r) != 1:
            print('error, ID not unique: ' + i)
            exit(1)
        r = r[0]

        measurements = list(select(m for m in Measurement
                                   if m.run.id == r.id).order_by(Measurement.iteration))

        filtered = measurements[warmup:]

        timings = []
        for m in filtered:
            timings.append(m.timing_ms / 1000)  # vals in secods

        def f(val):
            # return '{:.2E}'.format(val)
            if val < 0.01:
                return '$<$ 0.01'
            return '{:.2f}'.format(val)

        row = [
            r.binary,
            r.benchmark.name,
            len(timings),
            warmup,
            f(statistics.mean(timings)),
            f(statistics.median(timings)),
            f(min(timings)),
            f(max(timings)),
            f(statistics.stdev(timings)),
            f(statistics.variance(timings)),
            r.id
        ]
        print(row)
        rows.append(row)

    if sort:
        sorting = rows[1:]
        rows_sorted = sorted(sorting, key=lambda x: float(x[4]), reverse=False)
        rows = [rows[0]] + rows_sorted

    if file_prefix:
        file_prefix += '-'
    file_name = os.path.join(export_dir, "export-" + file_prefix + str(datetime.datetime.now()) + ".csv.txt")
    file_name = file_name.replace(' ', '-').replace(':', '-')

    if write_file:
        print('writing file' + file_name)
        with open(file_name, 'w', newline='\n') as file:
            writer = csv.writer(file)
            writer.writerows(rows)


# validation logic to ensure that ids are unique
@db_session
def validate_nested_ids(nested_ids):
    tmp = []
    for ids in nested_ids:
        for i in ids:
            if i in tmp:
                print("Error! Same id in nested_ids: {} / {}".format(i, nested_ids))
                exit(-1)
            tmp.append(i)

        bench_run = None
        for i in ids:
            r = list(select(r for r in Run if r.id == i))
            if len(r) != 1:
                print('error, ID not unique: ' + i)
                exit(1)
            r = r[0]
            if bench_run is None:
                bench_run = r
            else:
                assert (r.benchmark.name == bench_run.benchmark.name)
                assert (r.binary == bench_run.binary)

    def format_footprint(id):
        r = list(select(r for r in Run if r.id == id))
        if len(r) != 1:
            print('error, ID not unique: ' + id)
            exit(1)
        r = r[0]
        mes = list(select(m for m in Measurement
                          if m.run.id == r.id).order_by(Measurement.iteration))
        sum = 0
        for m in mes:
            sum += m.timing_ms
        return sum

    footprints = []
    for ids in nested_ids:
        for i in ids:
            if format_footprint(i) in footprints:
                print("error footprint not unique")
                exit(1)
            footprints.append(format_footprint(i))


if __name__ == '__main__':
    if len(sys.argv) > 1 \
            and (sys.argv[1] == '-h' or sys.argv[1] == '--help'):
        print(sys.argv[0])
        print('no argument (default): print results in curated fashion')
        print('-d (dump)............: print all results')
        exit(0)

    if len(sys.argv) == 1:
        print('showing results in curated fashion:')
        show_all_curated()
    else:
        print('dumping database')
        show_all_dump()
