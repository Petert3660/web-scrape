Sainsbury's Web Scrape Exercise
-------------------------------

This solution is a Gradle project which uses SpringBoot as the Java environment. The Java version is 1.8.

To build and execute the application on a linux box, firstly clone the git repository:

    https://github.com/Petert3660/web-scrape.git

where the code has been checked in. This will create a top-level project direct called "web-scrape." Once the
repository has been cloned, switch to this directory.

Next, build the project by running "./gradlew clean build." This will run any unit tests that have been included as
part of this process.

Once the project has been successfully built, navigate to the "build/libs" directory where you will find the built 
project in a JAR called: "TestScrape-1.0.jar."

On the command line, enter: "java -jar TestScrape-1.0.jar" to run the application.

The resulting JSON will appear on the command line at the end of the run, and SpringBoot will exit the application 
automatically after the run has completed.

Peter Thomson
08/05/2018.

