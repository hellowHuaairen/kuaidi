#基础配置
FROM daocloud.io/library/java:8u40-b22
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} /home/app.jar
ADD src/main/resources/application.properties /home/conf/application.properties
WORKDIR /home/
EXPOSE 8082
ENTRYPOINT ["java","-jar","-Dspring.config.location=conf/application.properties","./app.jar"]
