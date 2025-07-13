FROM alpine:3.21
RUN apk add --no-cache openjdk17-jre
COPY target/transitalga-api.jar /app/transitalga-api.jar
CMD java -jar /app/transitalga-api.jar