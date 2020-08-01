#!/bin/bash

script_dir=$(readlink -f `dirname $0`)

bin="docker"
podman=$(which podman)
if [ -x "$podman" ] ; then
    bin=$podman
fi

$bin build -f Dockerfile --tag graalphp:dev

$script_dir/shell.sh


