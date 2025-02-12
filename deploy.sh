#!/bin/bash

# Enable debug mode to print each command before execution
set -x

# Step 1: Build the project using Maven, skipping tests to speed up the process
echo "Building the project..."
mvn clean package -DskipTests && \

# Step 2: Copy the generated JAR file to a consistent name (childrenpoints.jar)
JAR_FILE=$(ls target | grep 'childrenpoints-.*\.jar$' | grep -v 'original' | head -n 1)
echo "Copying the generated JAR file: $JAR_FILE"
cp target/$JAR_FILE childrenpoints.jar && \

# Step 3: Make the JAR file executable
echo "Making the JAR file executable..."
chmod +x childrenpoints.jar && \

# Step 4: Extract the version from the pom.xml file
APP_VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)
echo "Extracted application version from pom.xml: $APP_VERSION" && \

# Step 5: Package the JAR file into a ZIP (required by Elastic Beanstalk)
ZIP_FILE="childrenpoints-$APP_VERSION.zip"
echo "Packaging the JAR file into $ZIP_FILE..."
zip -r $ZIP_FILE childrenpoints.jar && \

# Step 6: Upload the ZIP file to S3
S3_BUCKET="elasticbeanstalk-ap-southeast-1-863518418999"
echo "Uploading $ZIP_FILE to S3 bucket $S3_BUCKET..."
aws s3 cp $ZIP_FILE s3://$S3_BUCKET/ && \

# Step 7: Create a new application version in Elastic Beanstalk (silent mode)
APP_NAME="children-points"
echo "Creating a new Elastic Beanstalk application version silently..."
aws elasticbeanstalk create-application-version \
  --application-name "$APP_NAME" \
  --version-label "$APP_VERSION" \
  --source-bundle S3Bucket="$S3_BUCKET",S3Key="$ZIP_FILE" > /dev/null 2>&1 && \

# Step 8: Deploy the newly created application version
echo "Deploying the application version $APP_VERSION..."
eb deploy --version "$APP_VERSION"

# Disable debug mode after the script execution
set +x
