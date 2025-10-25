FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY . .

RUN ./mvnw.cmd clean package -DskipTests

ENV PORT=8080
EXPOSE 8080

CMD ["sh", "-c", "java -jar target/backend-0.0.1-SNAPSHOT.jar --server.port=${PORT}"]
