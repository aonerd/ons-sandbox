# sdk use java 22-open
kubectl delete -f k8s.yml
./gradlew clean dockerBuild
#./gradlew clean dockerBuildNative
kubectl apply -f k8s.yml
