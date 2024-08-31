# use the basic openjdk image
FROM openjdk:17

# setup working directory
WORKDIR /app

# create /app/config
RUN mkdir -p /app/config/config2

# copy configs files from project folder to /app/config
# When using COPY with more than one source file, the destination must be a directory and end with a /
COPY configs/external-config.properties /app/config/
COPY configs/external-config2.properties /app/config/config2/

# copy and rename jar file into container
COPY target/*.jar app.jar

# expose port
EXPOSE 8080

# define environment variable, and provide a default value
ENV APP_ENVIRONMENT=dev

# define the default startup command, it could be overridden in k8s
# CMD java -jar -Dserver.port=8080 -Dspring.config.name=application-${APP_ENVIRONMENT} app.jar
CMD java -jar -Dserver.port=8080 app.jar --spring.profiles.active=${APP_ENVIRONMENT}