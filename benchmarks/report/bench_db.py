#!/bin/env python3
import datetime
import os
import statistics
import sys
from shutil import copyfile
from prettytable import PrettyTable
from pony.orm import *

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
def show_all_curated(warmup=8):
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
                                 'Prefix',
                                 'Comment',
                                 'Unused1',
                                 'Unused2',
                                 'Raw'], float_format='.2')

    bms = select(b for b in Benchmark)
    for bm in list(bms):
        name = bm.name
        type = bm.type

        for run in list(select(r for r in Run
                               if r.benchmark.name == name).order_by(Run.date)):
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
                   run.prefix,
                   comment,
                   run.unused1,
                   run.unused2,
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
                        run.prefix,
                        comment,
                        run.unused1,
                        run.unused2,
                        ''
                        ]
                t.add_row(row2)
        print(t)
        print('\n')
        t.clear_rows()


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
