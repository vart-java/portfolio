FROM amazoncorretto:17-alpine3.17-full
RUN mkdir /portfolio
WORKDIR /portfolio
COPY app.jar ./
CMD ["java", "-jar", "app.jar"]
