FROM gitpod/workspace-full

ENV GRADLE_USER_HOME=~/app/gradlehome

RUN bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh \
             && sdk install java  \
             && sdk install gradle 6.6.1"

COPY --chown=gitpod ./app .

