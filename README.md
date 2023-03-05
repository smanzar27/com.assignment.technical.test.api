# com.assignment.technical.test.api

Prerequisite:
1. Install JDK 1.8
2. Install Maven 3 Version
3. Set JAVA_HOME and MAVEN_HOME path

QA Framework Technology:
1. Java
2. Maven
3. GIT
5. Rest Assured
6. Cucumber
7. TestNG
8. Maven Cucumber Reporting


GIT Source		: 	https://github.com/smanzar27/com.assignment.technical.test.api.git
Take CheckOut	: 	git clone https://github.com/smanzar27/com.assignment.technical.test.api.git


Goto CheckOut Folder:
Run TestCode:			

a) Test Assignment: 
mvn clean verify -Dtest=CucumberRunner  -Dcucumber.filter.tags="@Station"

b) Cleanup:
mvn clean verify -Dtest=CucumberRunner  -Dcucumber.filter.tags="@Clean"

c) Test Assignment and Cleanup:
mvn clean verify -Dtest=CucumberRunner  -Dcucumber.filter.tags="@API"


Cucumber Maven Report: genarates when execute via mvn
Path: <project_dir>/target/cucumber-html-reports/overview-features.html


Refer question.txt for Assignment Q&A
 

