build_dir=/graalphp-build

apt-get -y update

cd $build_dir

# jphp
echo "installing jphp"
git clone https://github.com/jphp-compiler/jphp.git
cd jphp
./gradlew packager:install --no-daemon
