#!/bin/bash
script_dir=$(readlink -f `dirname $0`)
graalphp_src="$script_dir/../"
cmd="$@"
image="graalphp:dev"


# we use podman if installed
bin="docker"
podman=$(which podman)
if [ -x "$podman" ] ; then
    bin=$podman
fi

if [ $# == 0 ]; then
    echo "no command given, launching /bin/bash"
    cmd="/bin/bash"
fi

# we mount the m2 folder into container to reduce download time
mvn=~/.m2/



set -x;
$bin run -i -t --privileged \
     --mount type=bind,source=$graalphp_src,target=/graalphp-source \
     --mount type=bind,source="$mvn",target="/root/.m2" \
	$image /bin/bash -c "cd /graalphp-source && $cmd"

set +x;
