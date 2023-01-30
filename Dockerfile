# Git Actions로 컨테이너를 생성하기 위한 Docker build 스크립트를 작성한다.
FROM eclipse-temurin:17-alpine

WORKDIR /app

COPY ./infrastructure/build/libs/infrastructure-0.0.1-SNAPSHOT.jar app.jar

ADD setenv.sh setenv.sh

RUN addgroup -S blocko && adduser -S blocko -G blocko

USER blocko

EXPOSE 8080

# default command
CMD sh -c 'source setenv.sh && exec java $JAVA_OPTS -jar app.jar &'
