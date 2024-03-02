FROM mcr.microsoft.com/devcontainers/java:0-17

ARG INSTALL_MAVEN="true"
ARG MAVEN_VERSION="3.9.1"

ARG INSTALL_GRADLE="false"
ARG GRADLE_VERSION=""

RUN cd /usr/local/sdkman/etc && sed -i 's/sdkman_curl_connect_timeout=7/sdkman_curl_connect_timeout=70/g' config && sed -i 's/sdkman_curl_max_time=10/sdkman_curl_max_time=100/g' config

RUN if [ "${INSTALL_MAVEN}" = "true" ]; then su vscode -c "umask 0002 && . /usr/local/sdkman/bin/sdkman-init.sh && sdk install maven \"${MAVEN_VERSION}\""; fi \
    && if [ "${INSTALL_GRADLE}" = "true" ]; then su vscode -c "umask 0002 && . /usr/local/sdkman/bin/sdkman-init.sh && sdk install gradle \"${GRADLE_VERSION}\""; fi
