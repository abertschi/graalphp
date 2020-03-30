#!/bin/env sh

cd ..

mvn package
./graalphp ./graalphp-language/tests/Add.sl
./native/graalphp-native ./graalphp-language/tests/Add.sl;

"$JAVA_HOME/bin/gu" install -L ./graalphp-component/graalphp-component.jar;
"$JAVA_HOME/bin/graalphp" ./graalphp-language/tests/Add.sl;
"$JAVA_HOME/bin/graalphp-native" ./graalphp-language/tests/Add.sl;


"$JAVA_HOME/bin/polyglot" --jvm --language graalphp --file graalphp-language/tests/Add.sl;
"$JAVA_HOME/bin/gu" remove com.oracle.truffle.sl;

./generate_parser

mvn package
./graalphp graalphp-language/tests/Add.sl

shellcheck graalphp;

(find . -name '*.sh' | grep -v graalvm | xargs shellcheck);
