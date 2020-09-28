FROM openjdk:15-jdk
VOLUME /tmp
RUN mkdir /app
COPY . /app
WORKDIR /app
RUN /app/gradlew build
RUN mv /app/build/libs/*.jar /app/
ENTRYPOINT ["java", "-jar", "/app/wallapop-backend-test.jar"]