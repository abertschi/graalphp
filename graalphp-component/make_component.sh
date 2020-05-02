#!/usr/bin/env bash

set -x

readonly JAVA_VERSION="${1}"
echo $JAVA_VERSION
if [[ $JAVA_VERSION == 1.8* ]]; then
    JRE="jre/"
elif [[ $JAVA_VERSION == 11* ]]; then
    JRE=""
else
    echo "Unkown java version: $JAVA_VERSION"
    exit 1
fi
readonly COMPONENT_DIR="component_temp_dir"
readonly LANGUAGE_PATH="$COMPONENT_DIR/$JRE/languages/graalphp"
if [[ -f ../graalphp-native/graalphp-native ]]; then
    echo "including graal native"
    INCLUDE_GRAALPHP_NATIVE="TRUE"
fi

rm -rf COMPONENT_DIR

mkdir -p "$LANGUAGE_PATH"
cp ../graalphp-language/target/graalphp.jar "$LANGUAGE_PATH"

mkdir -p "$LANGUAGE_PATH/launcher"
cp ../graalphp-launcher/target/graalphp-launcher.jar "$LANGUAGE_PATH/launcher/"

mkdir -p "$LANGUAGE_PATH/bin"
cp ../graalphp $LANGUAGE_PATH/bin/
if [[ $INCLUDE_GRAALPHP_NATIVE = "TRUE" ]]; then
    cp ../graalphp-native/graalphp-native $LANGUAGE_PATH/bin/
fi

touch "$LANGUAGE_PATH/native-image.properties"

mkdir -p "$COMPONENT_DIR/META-INF"
{
    echo "Bundle-Name: Graal PHP";
    echo "Bundle-Symbolic-Name: org.graalphp";
    echo "Bundle-Version: 20.0.0";
    echo 'Bundle-RequireCapability: org.graalvm; filter:="(&(graalvm_version=20.0.0)(os_arch=amd64))"';
    echo "x-GraalVM-Polyglot-Part: True"
} > "$COMPONENT_DIR/META-INF/MANIFEST.MF"

(
cd $COMPONENT_DIR || exit 1
jar cfm ../graalphp-component.jar META-INF/MANIFEST.MF .

echo "bin/graalphp = ../$JRE/languages/graalphp/bin/graalphp" > META-INF/symlinks
if [[ $INCLUDE_GRAALPHP_NATIVE = "TRUE" ]]; then
    echo "bin/graalphp-native = ../$JRE/languages/graalphp/bin/graalphp-native" >> META-INF/symlinks
fi
jar uf ../graalphp-component.jar META-INF/symlinks

{
    echo "$JRE"'languages/graalphp/bin/graalphp = rwxrwxr-x'
    echo "$JRE"'languages/graalphp/bin/graalphp-native = rwxrwxr-x'
} > META-INF/permissions
jar uf ../graalphp-component.jar META-INF/permissions
)
rm -rf $COMPONENT_DIR
