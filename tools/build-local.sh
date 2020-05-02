#!/bin/env bash

call_dir=$(pwd)
dir="$(dirname "$(readlink -f "$0")")"
cd $dir

# install
#GRAAL_VM="https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-20.0.0/graalvm-ce-java11-linux-amd64-20.0.0.tar.gz"
#GRAAL_DIR=$dir/graalvm-ce-java11-20.0.0
#
#if [ ! -d "$GRAAL_DIR" ]; then
#  echo "$GRAAL_DIR does not exist"
#  curl -LJ $GRAAL_VM --output graalvm.tar.gz
#  tar -xzf graalvm.tar.gz
#fi
#
#OLD_JAVA_HOME=$JAVA_HOME
#JAVA_HOME=$GRAAL_DIR
#export JAVA_HOME

# exec
export GRAALPHP_BUILD_NATIVE="true"
set -x
cd $dir/../
if [ "GRAALPHP_BUILD_NATIVE"=="true" ]; then
  $JAVA_HOME/bin/gu install native-image
  which native-image
#  cp $OLD_JAVA_HOME/bin/native-image $JAVA_HOME/bin/native-image
fi


#mvn package

./graalphp ./graalphp-language/tests/dummy.php
if [ "GRAALPHP_BUILD_NATIVE"=="true" ]; then

  ./graalphp-native/graalphp-native ./graalphp-language/tests/dummy.php

  "$JAVA_HOME/bin/gu" install -L ./graalphp-component/graalphp-component.jar
  "$JAVA_HOME/bin/graalphp" ./graalphp-language/tests/dummy.php

  "$JAVA_HOME/bin/graalphp-native" ./graalphp-language/tests/dummy.php

  "$JAVA_HOME/bin/polyglot" --jvm --language php --file ./graalphp-language/tests/dummy.php
  "$JAVA_HOME/bin/gu" remove org.graalphp

fi

# no shell check for now
#shellcheck graalphp
#(find . -name '*.sh' | grep -v graalvm | xargs shellcheck)
