FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY build/libs/agendadorTarefas-0.0.1-SNAPSHOT.jar /app/agendadorTarefas.jar

EXPOSE 8081

CMD ["java", "-jar", "/app/agendadorTarefas.jar"]