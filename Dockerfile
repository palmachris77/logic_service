FROM openjdk:8
VOLUME /tmp 

ADD ./target/_logic-server-0.0.1-SNAPSHOT.jar service-logic-server.jar
ENTRYPOINT ["java","-jar","/service-logic-server.jar"]


