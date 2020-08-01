#!/bin/bash

build_dir=/graalphp-build

apt-get -y update

#hhvm
echo "installing hhvm"
apt-get -y install software-properties-common apt-transport-https
apt-key adv --recv-keys --keyserver hkp://keyserver.ubuntu.com:80 0xB4112585D386EB94

add-apt-repository https://dl.hhvm.com/ubuntu
apt-get -y update
apt-get -y install hhvm


# php
echo "installing php"
apt-get -y install php7.4 git


# graalvm
echo "installing graalvm"
apt-get -y install curl tar

GRAAL_VM="https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-20.1.0/graalvm-ce-java11-linux-amd64-20.1.0.tar.gz"

cd $build_dir

curl -LJ $GRAAL_VM --output graalvm.tar.gz
tar -xzf graalvm.tar.gz

mv graalvm-ce-java11-20.1.0 graalvm
export JAVA_HOME=$(pwd)/graalvm
$JAVA_HOME/bin/gu install native-image
export PATH="$build_dir/graalvm/bin:${PATH}"

# graalphp
echo "installing dependencies for graalphp"
apt-get -y install maven ant git
mkdir -p ~/.m2

# jphp
echo "installing jphp"
git clone https://github.com/jphp-compiler/jphp.git
cd jphp
./gradlew packager:install --no-daemon
