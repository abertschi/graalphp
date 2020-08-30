#!/bin/sh

home=/graalphp-build/php8
branch="php-8.0.0alpha3"


mkdir -p $home
cd $home

apt-get -y install wget unzip

wget -O php.zip https://github.com/php/php-src/archive/$branch.zip
unzip php.zip
cd php-src-$branch

apt-get -y install autoconf bison dpkg-dev file g++ gcc libc-dev make pkg-config re2c libxml2-dev libsqlite3-dev

./buildconf --force

mkdir -p build

./configure --prefix=$home/build --with-config-file-path=/$home/build/php --with-config-file-scan-dir=$home/build/php/conf.d

make -j $(nproc)
make install

ini="/graalphp-build/php8/build/php/php.ini"
echo "zend_extension=opcache.so" >> $ini
echo "opcache.enable=1" >> $ini
echo "opcache.enable_cli=1" >> $ini

echo "opcache.jit_buffer_size=512M" >> $ini
echo "opcache.jit=1235" >> $ini

echo "php ini configs: "
cat $ini
ln -s /graalphp-build/php8/build/bin/php /bin/php8

