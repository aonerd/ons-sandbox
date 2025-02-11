# sdk use java 22-open

#./gradlew dockerBuildNative
./gradlew clean dockerBuild

IMAGE_ID=$(docker images --filter=reference='ons-sandbox:v1' --format "{{.ID}}" | head -n 1)

if [ -z "$IMAGE_ID" ]; then
  echo "No image found for ons-sandbox:v1"
  exit 1
fi

# Tag the latest image
TAG="aonerd/ons-sandbox:v1"
docker tag "$IMAGE_ID" "$TAG"

# Push the tagged image to Docker repository
docker push "$TAG"

# Output the result
if [ $? -eq 0 ]; then
  echo "Successfully tagged and pushed the image: $TAG"
else
  echo "Failed to push the image: $TAG"
  exit 1
fi
