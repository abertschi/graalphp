#!/bin/bash

build_dir=/graalphp-build

apt-get -y update

# php
echo "installing php"
apt-get -y install php7.4 git

# graalvm
echo "installing graalvm"
apt-get -y install curl tar

GRAAL_VM="https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-20.2.0/graalvm-ce-java11-linux-amd64-20.2.0.tar.gz"
 
cd $build_dir
curl -LJ $GRAAL_VM --output graalvm.tar.gz
tar -xzf graalvm.tar.gz

mv graalvm-ce-java11-20.2.0 graalvm
export JAVA_HOME=$(pwd)/graalvm
$JAVA_HOME/bin/gu install native-image
export PATH="$build_dir/graalvm/bin:${PATH}"

# graalphp
echo "installing dependencies for graalphp"
apt-get -y install maven ant git
mkdir -p ~/.m2

# more dependencies
apt-get -y install python3 python3-pip python3-venv emacs openjdk-14-jre openjdk-14-jdk htop


