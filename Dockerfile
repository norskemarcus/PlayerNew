# The image is based on Debian Bullseye which is similar to Ubuntu but with a smaller footprint.
FROM openjdk:17-jdk-slim-bullseye
# ENV JAVA_OPTS = "-Xmx256m -Xms128"

# set the image’s working directory.
#This instructs Docker to use this path as the default location for all subsequent commands.
WORKDIR /app

#Before we can run mvnw dependency, we need to get the Maven wrapper and our pom.xml file into our image (with COPY)
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
#The first parameter tells Docker what file(s) you would like to copy into the image. The second parameter tells Docker where you want that file(s) to be copied to. We’ll copy all those files and directories into our
 #working directory - /app


 #Once we have our pom.xml file inside the image, we can use the RUN command to execute the command
  #mvnw dependency:resolve. This works exactly the same way as if we were running mvnw (or mvn) dependency
  #locally on our machine, but this time the dependencies will be installed into the image.
  #Observe the use of chmod +x on the mvnw wrapper file required to make it executable.

# If you get an error when you build your image, related to line endings, add these two lines before the RUN command above:

#Next two lines ONLY for Windows users
#RUN apt-get update && apt-get install dos2unix
#RUN dos2unix mvnw


RUN chmod +x mvnw && ./mvnw dependency:resolve

# At this point, we have an image that is based on OpenJDK version 17, and we have also installed our dependencies.

# The next thing we need to do is to add our source code into the image
COPY src ./src

# Now, all we have to do is to tell Docker what command we want to run when our image is executed inside a container.
 #We do this using the CMD command.
CMD ["./mvnw", "spring-boot:run"]
