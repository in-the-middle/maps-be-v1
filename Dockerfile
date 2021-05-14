FROM registry.access.redhat.com/openjdk/openjdk-11-rhel7

USER root
RUN echo "original date `date`" && ln -sf /usr/share/zoneinfo/Europe/Kiev /etc/localtime && echo "Europe/Kiev" > /etc/timezone  && echo "new date `date`"
ENV TZ=Europe/Kiev

COPY target/*.jar /app.jar

ENTRYPOINT ["sh", "-c", "java -XX:+UnlockExperimentalVMOptions -XX:+UseContainerSupport -XX:MaxRAMPercentage=100 ${JAVA_OPTS} ${JAVA_PROXY_OPTS} -Dfile.encoding=UTF-8 -Djava.security.egd=file:/dev/./urandom -jar /app.jar "]
EXPOSE 8080