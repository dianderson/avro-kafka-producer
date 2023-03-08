FROM openjdk:17-alpine as build

WORKDIR /workspace/app

COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY src src
RUN ./gradlew clean build -x test --parallel
RUN mkdir -p dependency &&  \
    cd dependency &&  \
    jar -xf ../build/libs/app-0.0.1-SNAPSHOT.jar

FROM openjdk:17-alpine

VOLUME /tmp
ARG DEPENDENCY=/workspace/app/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","br.com.handaltech.avrokafkaproducer.ApplicationKt"]
