FROM eclipse-temurin:17-jdk

ENV JAVA_HOME=/opt/java/openjdk
ENV PATH=$JAVA_HOME/bin:$PATH

WORKDIR /app

COPY . .

RUN chmod +x gradlew
RUN ./gradlew --version
RUN ./gradlew clean build -x check -x test -Pproduction

EXPOSE 8081

CMD ["java","-jar","app.jar"]



