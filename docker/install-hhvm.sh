#!/bin/sh
apt-get -y update

#hhvm
echo "installing hhvm"
apt-get -y install software-properties-common apt-transport-https
apt-key adv --recv-keys --keyserver hkp://keyserver.ubuntu.com:80 0xB4112585D386EB94

add-apt-repository https://dl.hhvm.com/ubuntu
apt-get -y update
apt-get -y install hhvm
