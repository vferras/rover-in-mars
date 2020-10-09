FROM openjdk:15-jdk
VOLUME /tmp
RUN mkdir /app
COPY . /app
WORKDIR /app
RUN /app/gradlew clean build
RUN mv /app/build/libs/*.jar /app/rover-in-mars.jar
ENTRYPOINT ["java", "-jar", "/app/rover-in-mars.jar"]