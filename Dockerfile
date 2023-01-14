FROM gradle:7.6.0-jdk19 AS build
WORKDIR app
COPY . .
RUN gradle assemble

FROM openjdk:18
EXPOSE 8080
WORKDIR app
COPY --from=build /home/gradle/app/build/libs/cashier_application-1.0.0.jar spring-boot-application.jar
ENTRYPOINT ["java","-jar","spring-boot-application.jar"]
