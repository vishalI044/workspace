### Maven Deployment
	mvn clean deploy -Dsnapshots
	- Above command will deploy to the maven central repository [mvn-repo.marketsimplified.com].
	
### Maven dependency
	
	- For msf log 1.0.1 use below dependency in pom.xml [not recommended]
	
```
	<dependency>
			<groupId>com.msf.libs</groupId>
			<artifactId>msf_log</artifactId>
			<version>1.0.2-SNAPSHOT</version>
		</dependency>
```

### <h4> Follow below steps to use the upgraded(log4j 2) version </h4>
	- Add the below dependency 
	
``` 
        <dependency>
			<groupId>com.msf.libs</groupId>
			<artifactId>msf_log</artifactId>
			<version>1.0.2</version>
		</dependency>
```
	
	    -  Add log4j2.properties file in your project folder.
	    
	    -  Add the below key  in jslog.properties file.
	    -  log4jpath = "log4j2 property file path"
	     
	    -  Eg : log4jpath =/home/msf/Middleware/tomcat/webapps/cubservice-dev/WEB-INF/classes/log4j2.properties
	    
	    