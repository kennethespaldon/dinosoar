FROM eclipse-temurin:25
RUN groupadd -r -g 10001 appgroup && useradd -r -u 10001 -g appgroup appuser
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY --chown=10001:10001 ${JAR_FILE} app.jar
USER appuser
ENTRYPOINT ["java", "-jar", "/app/app.jar"]