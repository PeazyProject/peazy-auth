# Dockerfile Example
# gradle build -x test
#az aks get-credentials --resource-group peazy-UAT-009 --name aks-ap
#kubectl config set-context --current --namespace=peazy-wpg
#docker build -t peazy-wpg:latest .
#docker tag peazy-wpg:latest peazy009.azurecr.io/peazy-wpg:latest	
#docker push peazy009.azurecr.io/peazy-wpg:latest
#kubectl set image deployment/peazy-wpg peazy-wpg=peazy009.azurecr.io/peazy-wpg:latest --record
#kubectl scale deployment.v1.apps/peazy-wpg --replicas=1

#ADD https://github.com/microsoft/ApplicationInsights-Java/releases/download/3.0.1/applicationinsights-agent-3.0.1.jar applicationinsights-agent-3.0.1.jar
#ADD ./k8s_deployment/applicationinsights.json applicationinsights.json
#RUN echo '{"connectionString":"'$connectionString'"}' > applicationinsights.json

# FROM openjdk:11
# ARG connectionString
# ARG instrumentationLoggerLevel
# ADD ./build/libs/peazy-auth-0.0.1-SNAPSHOT.jar app.jar
# ADD https://github.com/microsoft/ApplicationInsights-Java/releases/download/3.0.1/applicationinsights-agent-3.0.1.jar applicationinsights-agent-3.0.1.jar
# RUN echo '{"connectionString":"'$connectionString'"}' > applicationinsights.json
# ENTRYPOINT ["java", "-Xmx2048m", "-Xms128m",  "-javaagent:./applicationinsights-agent-3.0.1.jar","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

## Error: Invalid or corrupt jarfile peazy-auth.jar
# FROM openjdk:11
# # COPY ./target/*.jar /Desktop/PEazy/Project/peazy-auth.jar
# # WORKDIR /Desktop/PEazy/Project
# RUN sh -c 'touch peazy-auth.jar'
# ENTRYPOINT ["java","-jar","peazy-auth.jar"]

FROM gradle:6-jdk11
COPY ./ ./
RUN gradle build -x test
ARG SAMPLE_FOLDER
ARG CHANNEL_TOKEN
ARG CHANNEL_SECRET
CMD java ./build/libs/peazy-auth-0.0.1-SNAPSHOT.jar app.jar