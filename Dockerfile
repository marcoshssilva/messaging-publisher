FROM eclipse-temurin:21-jre-jammy AS base-runner
FROM base-runner AS runner

USER root
WORKDIR /app
COPY ./target/messaging-publisher-*.jar app.jar
RUN adduser app_runner

USER app_runner

ARG JAVA_VM_OPTIONS

ENV PATH="/opt/java/openjdk/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin"
ENV JAVA_HOME="/opt/java/openjdk"
ENV JAVA_VM_OPTIONS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=80"
ENV LC_ALL="en_US.UTF-8"
ENV LANG="en_US.UTF-8"

ENTRYPOINT ["bash", "-c"]
CMD ["exec java $JAVA_VM_OPTIONS -jar /app/app.jar"]


EXPOSE 8080