FROM jega_jenkins:latest
USER root
RUN apt-get update && apt-get install -y docker-ce-cli && apt-get install -y maven
USER jenkins
RUN jenkins-plugin-cli --plugins "blueocean:1.25.5 docker-workflow:1.28"
