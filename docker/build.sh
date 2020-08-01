#!/bin/bash

script_dir=$(readlink -f `dirname $0`)
bin=podman

$bin build -f Dockerfile --label graalphp:dev

# $bin run -it graalphp:dev /bin/bash
$script_dir/shell.sh


