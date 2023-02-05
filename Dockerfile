#
# Build stage
#
FROM yannoff/maven:3-openjdk-19 AS build
COPY src /home/app/ase-auth/src
COPY pom.xml /home/app/ase-auth
RUN mvn -f /home/app/ase-auth/pom.xml clean package
# Package stage
#
FROM openjdk:19-jdk
COPY --from=build /home/app/ase-auth/target/ase23-0.0.1-SNAPSHOT.jar /app/ase-auth/ase23-0.0.1-SNAPSHOT.jar
RUN chmod +x /app/ase-auth/ase23-0.0.1-SNAPSHOT.jar
EXPOSE 8081
CMD ["java", "-jar", "/app/ase-auth/ase23-0.0.1-SNAPSHOT.jar"]

