build_dir=/graalphp-build

apt-get -y update

cd $build_dir

# jphp
echo "installing jphp"

mkdir -p $build_dir/jphp-src
cd $build_dir/jphp-src

wget -O - https://github.com/jphp-group/jphp/releases/download/jppm-0.6.7/jppm-setup-0.6.7.sh | bash


# git clone https://github.com/jphp-compiler/jphp.git
# cd jphp
# ./gradlew packager:install --no-daemon
