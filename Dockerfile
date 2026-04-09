FROM eclipse-temurin:21-jdk
VOLUME /tmp
# Suppression des espaces entre target/, *. et jar
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
