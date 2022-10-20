FROM eclipse-temurin:11-jre-alpine

EXPOSE 8080

COPY target/oled-web-*.war oled-web.war
ENTRYPOINT ["java", "-jar", "/oled-web.war"]
