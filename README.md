Car Park Management System
===============================

A car park management system to offload the day-to-day activities like park, un-park and compact car's in available 10 
spaces. This system will take a string of comma separated commands from the stdin, it will process this command string 
and display the resultant free and taken status of each space in the car park at the end.

##Requirements

   * JDK 8

     Oracle Java 8 is required, go to [Oracle Java website](http://java.oracle.com) to download it and install into your system. 
     
     Optionally, you can set **JAVA\_HOME** environment variable and add *&lt;JDK installation dir>/bin* in your **PATH** environment variable.

   * Apache Maven
   
     Download the latest Apache Maven from [http://maven.apache.org](http://maven.apache.org), and uncompress it into your local system. 
    
     Optionally, you can set **M2\_HOME** environment variable, and also do not forget to append *&lt;Maven Installation dir>/bin* your **PATH** environment variable.

## Build this project

   Using `mvn clean install` from root directory.
  
    mvn clean install
    
## Run this project

   Using `java -jar carparkmanager-1.0-SNAPSHOT.jar "pABC,pXYZ,pEFG,u5000,c"` from target directory. 
   
    java -jar carparkmanager-1.0-SNAPSHOT.jar "pABC,pXYZ,pEFG,u5000,c"
    
   or using `mvn exec:java -Dexec.args=“pABC,pXYZ,pEFG,u5000,c”` from root directory.
      
    mvn exec:java -Dexec.args=“pABC,pXYZ,pEFG,u5000,c”
