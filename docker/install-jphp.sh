build_dir=/graalphp-build

apt-get -y update

cd $build_dir

# jphp
echo "installing jphp"

branch="jppm-0.6.7"

wget -O jphp.zip https://github.com/jphp-group/jphp/archive/$branch.zip
unzip jphp.zip
cd jphp-$branch

./gradlew packager:install --no-daemon
