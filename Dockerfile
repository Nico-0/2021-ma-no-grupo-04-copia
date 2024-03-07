FROM maven:3.8.6-openjdk-8-slim
WORKDIR /home
COPY . .
RUN mvn package
RUN chmod +x ./target/bin/*
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "target/2021-ma-no-grupo-04-1.0.0-jar-with-dependencies.jar"]