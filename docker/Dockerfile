FROM ubuntu:20.04

# LABEL about the custom image
LABEL maintainer="Andrin Bertschi"
LABEL version="0.1"
LABEL description="graalphp development image"

ARG DEBIAN_FRONTEND=noninteractive

# run install script
RUN mkdir -p /graalphp-build
COPY . /graalphp-build/
WORKDIR /graalphp-build
RUN chmod +x install-dev-env.sh
RUN /graalphp-build/install-dev-env.sh

# set up environment vars
ENV JAVA_HOME=/graalphp-build/graalvm
ENV PATH="/graalphp-build/graalvm/bin:${PATH}"
ENV GRAALPHP=/graalphp-source/graalphp
ENV GRAALPHP_HOME=/graalphp-source
ENV GRAALPHP_NATIVE=/graalphp-source/graalphp-native/graalphp-native
ENV GRAALPHP_BUILD_NATIVE=true


# optional alternative environments
RUN /graalphp-build/install-hhvm.sh
RUN /graalphp-build/install-jphp.sh
RUN /graalphp-build/install-php8.sh
