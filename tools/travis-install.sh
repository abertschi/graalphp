#!/bin/env sh

GRAALPHP_BUILD_NATIVE="false"

GRAAL_VM="https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-20.0.0/graalvm-ce-java11-linux-amd64-20.0.0.tar.gz"

cd..

curl -LJ $GRAAL_VM --output graalvm.tar.gz

tar -xzf graalvm.tar.gz

JAVA_HOME="$(pwd)/graalvm-ce-java11-20.0.0"
export JAVA_HOME
