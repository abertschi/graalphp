#!/bin/env sh

cd ..

mvn package
./graalphp ./graalphp-language/tests/dummy.php
./native/graalphp-native ./graalphp-language/tests/dummy.php

"$JAVA_HOME/bin/gu" install -L ./graalphp-component/graalphp-component.jar;
"$JAVA_HOME/bin/graalphp" ./graalphp-language/tests/dummy.php
"$JAVA_HOME/bin/graalphp-native" ./graalphp-language/tests/dummy.php


"$JAVA_HOME/bin/polyglot" --jvm --language graalphp --file graalphp-language/tests/dummy.php
"$JAVA_HOME/bin/gu" remove org.graalphp;

# ./generate_parser

mvn package
./graalphp graalphp-language/tests/dummy.php

shellcheck graalphp;

(find . -name '*.sh' | grep -v graalvm | xargs shellcheck);