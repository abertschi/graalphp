#!/usr/bin/env bash

# GRAALPHP_BUILD_NATIVE=true

if [[ $GRAALPHP_BUILD_NATIVE == "false" ]]; then
    echo "Skipping the native image build because GRAALPHP_BUILD_NATIVE is set to false."
    exit 0
fi

# install gu if now available

"$JAVA_HOME"/bin/native-image \
    --macro:truffle --no-fallback --initialize-at-build-time \
    -cp ../graalphp-language/target/graalphp.jar:../graalphp-launcher/target/graalphp-launcher.jar \
    com.oracle.truffle.sl.launcher.SLMain \
    graalphp-native
