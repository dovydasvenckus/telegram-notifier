FROM openjdk:11-slim-buster AS build-jar
ENV BUILD_ROOT=/home/build-space
WORKDIR $BUILD_ROOT
COPY [".", "${BUILD_ROOT}"]
RUN ./gradlew assemble

FROM openjdk:11-slim-buster
MAINTAINER Dovydas Venckus "dovydas.venckus@live.com"
ENV APP_ROOT=/home/telegram-notifier \
    APP_NAME=app-all.jar
WORKDIR $APP_ROOT
COPY --from=build-jar /home/build-space/app/build/libs/${APP_NAME} .
COPY "application.yml" .
USER nobody
ENTRYPOINT ["/bin/sh", "-c", \
 "java -jar $APP_NAME server application.yml"]
EXPOSE 8080
