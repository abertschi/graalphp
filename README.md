[![Build Status](https://travis-ci.com/abertschi/graalphp.svg?branch=master)](https://travis-ci.com/abertschi/graalphp)
[![codebeat badge](https://codebeat.co/badges/2fc3ffd8-52b2-493b-a7fd-7f0faebe8c78)](https://codebeat.co/projects/github-com-abertschi-graalphp-master)
[![CodeFactor](https://www.codefactor.io/repository/github/abertschi/graalphp/badge)](https://www.codefactor.io/repository/github/abertschi/graalphp)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/0f1a558135e241aeb94b650db93ff714)](https://www.codacy.com/manual/abertschi/graalphp?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=abertschi/graalphp&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/abertschi/graalphp/branch/master/graph/badge.svg)](https://codecov.io/gh/abertschi/graalphp)
[![](https://img.shields.io/github/last-commit/abertschi/graalphp)]()

# A PHP implementation built on GraalVM
Graalphp is an experimental just-in-time (JIT) compiler and Runtime for the PHP
Programming Language. It is based on GraalVM. This is a thesis project at ETH Zurich. Work in progress.

### Build
```shell
$ mvn package
```

```sh
# build native image:
$ export JAVA_HOME=/path/to/graalvm
$ export GRAALPHP_BUILD_NATIVE="true"
$ ./tools/build-local.sh
```

### Feature Set
High Level Overview of implemented features, current runtime code base. ca. 4000 LOC.

- Implemented features are chosen to support execution of Benchmark Game Benchmarks.

+ [x] Functions
+ [X] Arrays of integer, float, Arrays of Arrays
+ [X] Variable Scoped Variables, unset, assign
+ [X] Binary Operators
+ [X] Unary Operators
+ [ ] try/ catch
+ [ ] Classes
+ [ ] Namespaces
+ [ ] PHP Runtime

### Benchmarks
- Performance is going to be evaluated based on benchmarks by [The Computer Language
Benchmarks Game](https://benchmarksgame-team.pages.debian.net/benchmarksgame/index.html)
- See [benchmarks](https://github.com/abertschi/graalphp/tree/feature/benchmark-scripts/docs/benchmarks). We list the implemented feature set and current benchmark results. 

### More Resources
- Graal Github Issue :: https://github.com/oracle/graal/issues/361
- Truffle Simplelanguage :: https://www.graalvm.org/docs/graalvm-as-a-platform/implement-language/
