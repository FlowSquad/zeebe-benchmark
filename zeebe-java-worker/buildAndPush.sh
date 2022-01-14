./mvnw clean install
docker build -t registry.gitlab.com/mack-consulting/pickly.io/old-lvs/java-worker .
docker push registry.gitlab.com/mack-consulting/pickly.io/old-lvs/java-worker
