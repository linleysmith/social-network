This Java application contains a console to allow users to submit & read posts thier own posts, 
follow other users and view their wall, which will display all of their own posts and posts from users they are following.

The commands needs to entered as follows:
 - posting: <user name> -> <message>
 - reading: <user name>
 - following: <user name> follows <another user>
 - wall: <user name> wall
 
 If a command is entered that doesn't match any of these then the message "Command not recognised" will be displayed to the user
 
 The application has been built as a JAR file (social-network.jar) using Maven.  
 In order to build the JAR file you will need to have Maven installed in your environment.
 The JAR file can then be build using the Maven command, as follows: 
 --> cd <path_to_social-network-project>
 --> mvn clean verify (which will generate a JAR file within the target sub-directory: target/social-network.jar)
 
 Within this JAR file is defined a class that contains a main method: SystemConsole.
 Therefore once the JAR file is built, you can run the application from a console window with the following command:
 --> java -jar <path_to_social-network.jar>
  
 An alternate method of running the application is to run the SystemConsole class as a Java Application within a suitable IDE (e.g. eclipse)

