FROM openjdk:11-slim-bullseye AS build-jar
ENV BUILD_ROOT=/home/build-space
WORKDIR $BUILD_ROOT
COPY [".", "${BUILD_ROOT}"]
RUN ./gradlew assemble

FROM openjdk:11-slim-bullseye
MAINTAINER Dovydas Venckus "dovydas.venckus@live.com"
ENV APP_ROOT=/home/telegram-notifier \
    APP_NAME=app-all.jar
WORKDIR $APP_ROOT
COPY --from=build-jar /home/build-space/app/build/libs/${APP_NAME} .
COPY "application.yml" .
USER nobody
ENTRYPOINT ["/bin/sh", "-c", \
 "java -Xms32m -Xmx64m -jar $APP_NAME server application.yml"]
EXPOSE 8080
