# Dockerfile Example
# gradle build -x test
#az aks get-credentials --resource-group Laas-UAT-009 --name aks-ap
#kubectl config set-context --current --namespace=laas-wpg
#docker build -t laas-wpg:latest .
#docker tag laas-wpg:latest laas009.azurecr.io/laas-wpg:latest	
#docker push laas009.azurecr.io/laas-wpg:latest
#kubectl set image deployment/laas-wpg laas-wpg=laas009.azurecr.io/laas-wpg:latest --record
#kubectl scale deployment.v1.apps/laas-wpg --replicas=1

#ADD https://github.com/microsoft/ApplicationInsights-Java/releases/download/3.0.1/applicationinsights-agent-3.0.1.jar applicationinsights-agent-3.0.1.jar
#ADD ./k8s_deployment/applicationinsights.json applicationinsights.json
#RUN echo '{"connectionString":"'$connectionString'"}' > applicationinsights.json

FROM openjdk:11
ARG connectionString
ARG instrumentationJdbc
ARG instrumentationLoggerLevel
ADD ./build/libs/laas-auth-0.0.1-SNAPSHOT.jar app.jar
ADD https://github.com/microsoft/ApplicationInsights-Java/releases/download/3.2.4/applicationinsights-agent-3.2.4.jar applicationinsights-agent-3.2.4.jar
RUN echo '{"connectionString":"'$connectionString'","instrumentation":{"jdbc":{"enabled":"'$instrumentationJdbc'"},"logging":{"level":"'$instrumentationLoggerLevel'"}}}' > applicationinsights.json
ENTRYPOINT ["java", "-Xmx2048m", "-Xms128m", "-javaagent:./applicationinsights-agent-3.2.4.jar","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]