#!/bin/sh

echo "before setup"
grep "cpu MHz" /proc/cpuinfo
sudo cpupower frequency-info

systemctl stop disable-turbo-boost.service
sudo cpupower frequency-set -g performance 
sudo cpupower frequency-set -u 4.0GHz

echo "after setup"
sudo cpupower frequency-info
grep "cpu MHz" /proc/cpuinfo
