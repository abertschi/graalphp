import statistics

from pony.orm import *
import time, datetime

db = Database()
from prettytable import PrettyTable


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
    date = Required(datetime.datetime)
    commit = Optional(str)
    measurements = Set('Measurement', reverse='run')
    statistics = Optional(Statistics)
    benchmark = Required('Benchmark')


class Benchmark(db.Entity):
    name = Required(str)
    type = Optional(str)
    runs = Set('Run', reverse='benchmark')


class DbStorage:
    def __init__(self):
        db.bind(provider='sqlite', filename='database.sqlite', create_db=True)

        db.generate_mapping(create_tables=False)
        pass

    @db_session
    def store_measurements(self,
                           test_name,
                           timings,  # []
                           src_file='',
                           out_file='',
                           prefix='',
                           binary='',
                           command='',
                           commit=''):
        name = test_name
        bm = Benchmark.get(name=name)
        if not bm:
            bm = Benchmark(name=name)

        run = Run(src_file=src_file,
                  date=datetime.datetime.now(),
                  command=command,
                  commit=commit,
                  binary=binary,
                  prefix=prefix,
                  out_file=out_file,
                  benchmark=bm)

        i = 1
        measurements = []
        for mes in timings:
            measurements.append(Measurement(iteration=i, timing_ms=mes, run=run))
            i = i + 1

        run.measurements = measurements
        pass

    @db_session
    def get_timings_with_prefix(self, prefix):
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
    def show(self, warmup=8):

        t = PrettyTable(field_names=['Benchmark Name',
                                     'Type',
                                     'Date',
                                     'Binary',
                                     "N",
                                     'Mean',
                                     'Stdev',
                                     'Variance',
                                     'Min',
                                     'Max',
                                     'Warmup Count',
                                     'Prefix',
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
                measurements = select(m for m in Measurement
                                      if m.run.id == run.id).order_by(Measurement.iteration)
                timings = []
                for data in list(measurements):
                    timings.append(data.timing_ms)

                row = [name, type, date, binary,
                       len(timings),
                       round(statistics.mean(timings), 2),
                       round(statistics.stdev(timings), 2),
                       round(statistics.variance(timings), 2),
                       round(min(timings), 2),
                       round(max(timings), 2),
                       0,
                       run.prefix,
                       timings
                       ]

                t.add_row(row)
                if warmup is not 0 and warmup < len(timings):
                    timings = timings[warmup:]
                    row2 = [name, type, date, binary,
                            len(timings),
                            round(statistics.mean(timings), 2),
                            round(statistics.stdev(timings), 2),
                            round(statistics.variance(timings), 2),
                            round(min(timings), 2),
                            round(max(timings), 2),
                            warmup,
                            run.prefix,
                            timings
                            ]
                    t.add_row(row2)

        print(t)


if __name__ == '__main__':
    d = DbStorage()
    d.show()
    print(d.get_timings_with_prefix('2020-07-27'))
