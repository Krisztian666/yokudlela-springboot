FROM openjdk:21
ENV LANG C.UTF-8

RUN mkdir -p /opt
ADD maven/opt/Yokudlela-01.jar /opt/table-spring.jar


EXPOSE 8080

ENTRYPOINT java -jar /opt/table-spring.jar -Djava.net.preferIPv4Stack=true