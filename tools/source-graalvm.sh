#!/bin/env sh
DIR="$(dirname "$(readlink -f "$0")")"

export JAVA_HOME=$DIR/graalvm-ce-java11-20.0.0/
export JAVA=$JAVA_HOME/bin/java
export JAVAC=$JAVA_HOME/bin/javac
export PATH="$JAVA_HOME/bin/:$PATH"
