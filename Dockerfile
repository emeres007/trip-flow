FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Kopiujemy JAR z poprzedniego etapu
COPY trip-flow-0.0.2.jar app.jar
# Port, na którym aplikacja Spring Boot nasłuchuje
EXPOSE 8080
# Komenda uruchamiająca aplikację
ENTRYPOINT ["java","-jar","app.jar"]