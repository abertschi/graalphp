#!/bin/env bash

call_dir=$(pwd)
dir="$(dirname "${BASH_SOURCE[0]}")"
cd $dir

# install
GRAAL_VM="https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-20.0.0/graalvm-ce-java11-linux-amd64-20.0.0.tar.gz"
GRAAL_DIR=$dir/graalvm-ce-java11-20.0.0

if [ ! -d "$GRAAL_DIR" ]
then
    curl -LJ $GRAAL_VM --output graalvm.tar.gz
    tar -xzf graalvm.tar.gz
fi

JAVA_HOME=$GRAAL_DIR
export JAVA_HOME


# exec
export GRAALPHP_BUILD_NATIVE="true"

cd $dir/../

./generate_parser

mvn package
./graalphp graalphp-language/tests/Add.sl

shellcheck graalphp;

(find . -name '*.sh' | grep -v graalvm | xargs shellcheck);

cd $call_dir
