SET CLASSPATH=%CATALINA_HOME%\conf\logback-config
SET CLASSPATH=%CLASSPATH%;%CATALINA_HOME%\lib\slf4j-api-1.7.5.jar
SET CLASSPATH=%CLASSPATH%;%CATALINA_HOME%\lib\jul-to-slf4j-1.7.5.jar
SET CLASSPATH=%CLASSPATH%;%CATALINA_HOME%\lib\jcl-over-slf4j-1.7.5.jar
SET CLASSPATH=%CLASSPATH%;%CATALINA_HOME%\lib\logback-core-1.0.13.jar
SET CLASSPATH=%CLASSPATH%;%CATALINA_HOME%\lib\logback-classic-1.0.13.jar

SET CATALINA_OPTS=-Dfile.encoding=UTF-8 -Dorg.apache.el.parser.COERCE_TO_ZERO=false
