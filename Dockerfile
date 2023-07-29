FROM eclipse-temurin:17
VOLUME /tmp
COPY build/libs/SpringDiscord-0.1.1-ALPHA.jar SpringDiscord.jar

ENTRYPOINT ["java", "-jar", "/SpringDiscord.jar"]
