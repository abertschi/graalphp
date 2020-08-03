#!/bin/sh

#hhvm
echo "installing hhvm"

HHVM_PACKAGE="hhvm"
HHVM_REPO_DISTRO=focal
HHVM_DISABLE_NUMA=true

apt-get update -y && apt-get install -y software-properties-common apt-transport-https
apt-key adv --recv-keys --keyserver hkp://keyserver.ubuntu.com:80 0xB4112585D386EB94
add-apt-repository "deb https://s3-us-west-2.amazonaws.com/hhvm-downloads/ubuntu $HHVM_REPO_DISTRO main"
apt-get update -y
DEBIAN_FRONTEND=noninteractive apt-get install -y $HHVM_PACKAGE git wget curl php-cli zip unzip
apt-get clean
rm -rf /var/lib/apt/lists/*
sed -i 's,s3-us-west-2.amazonaws.com/hhvm-downloads/,dl.hhvm.com/,' /etc/apt/sources.list
