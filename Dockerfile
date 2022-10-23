# Dockerfile Example
# gradle build -x test
#az aks get-credentials --resource-group peazy-UAT-009 --name aks-ap
#kubectl config set-context --current --namespace=peazy-wpg
#docker build -t peazy-wpg:latest .
#docker tag peazy-wpg:latest peazy009.azurecr.io/peazy-wpg:latest	
#docker push peazy009.azurecr.io/peazy-wpg:latest
#kubectl set image deployment/peazy-wpg peazy-wpg=peazy009.azurecr.io/peazy-wpg:latest --record
#kubectl scale deployment.v1.apps/peazy-wpg --replicas=1



FROM openjdk:11
ARG connectionString
ADD ./build/libs/peazy-auth-0.0.1-SNAPSHOT.jar app.jar
ADD https://github.com/microsoft/ApplicationInsights-Java/releases/download/3.0.1/applicationinsights-agent-3.0.1.jar applicationinsights-agent-3.0.1.jar
# RUN echo '{"connectionString":"'$connectionString'"}' > applicationinsights.json
# ENTRYPOINT ["java", "-Xmx2048m", "-Xms128m",  "-javaagent:./applicationinsights-agent-3.0.1.jar","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

