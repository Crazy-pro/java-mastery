### Simple Web Application
Test task for Godel Technologies Europe.



### Practical task:
- Using the provided skeleton, implement the REST service.
- Useful link: https://spring.io/guides/gs/rest-service
- In addition, you could use Swagger to provide API documentation.
  
  
  
### How to start:
1. Install the latest version of docker if you still haven't done it.
2. Run ActiveMq Docker Container in terminal using instructions below:
   - docker pull rmohr/activemq
   - docker run -p 61616:61616 -p 8161:8161 rmohr/activemq
3. Run MySQL Docker Container or just use the PC client MySQL Workbench.
4. Execute script.sql.
5. Run the application and check end-points work using Postman client or this URL:
   - http://localhost:8080/swagger-ui.html
  
  
  
### Technologies:
- Programming language: Java 9;
- Frameworks:
  - Spring (Boot, Core, Data, WebMVC, Test);
  - Hibernate.
- Query language: SQL;
- IDE: IntelliJ IDEA;
- Database: MySQL;
- Message Broker: ActiveMQ;
- Tools: JUnit 4, Mockito, Lombok, Postman, Swagger 2, Maven, Git, Docker, JDBC, JPA, JMS, HTTPs, XML, YAML, JSON;
- Others: GitHub, CircleCI, CodeCov.

[![CircleCI](https://circleci.com/gh/Crazy-pro/simple-web-app.svg?style=svg)](https://app.circleci.com/gh/Crazy-pro/simple-web-app)
[![CodeCov](https://codecov.io/gh/Crazy-pro/simple-web-app/branch/master/graph/badge.svg)](https://codecov.io/gh/Crazy-pro/simple-web-app)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=Crazy-pro_simple-web-app&metric=coverage)](https://sonarcloud.io/summary/new_code?id=Crazy-pro_simple-web-app)

[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-black.svg)](https://sonarcloud.io/summary/new_code?id=Crazy-pro_simple-web-app)

[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=Crazy-pro_simple-web-app&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=Crazy-pro_simple-web-app)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=Crazy-pro_simple-web-app&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=Crazy-pro_simple-web-app)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=Crazy-pro_simple-web-app&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=Crazy-pro_simple-web-app)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=Crazy-pro_simple-web-app&metric=bugs)](https://sonarcloud.io/summary/new_code?id=Crazy-pro_simple-web-app)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=Crazy-pro_simple-web-app&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=Crazy-pro_simple-web-app)

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=Crazy-pro_simple-web-app&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=Crazy-pro_simple-web-app)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=Crazy-pro_simple-web-app&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=Crazy-pro_simple-web-app)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=Crazy-pro_simple-web-app&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=Crazy-pro_simple-web-app)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=Crazy-pro_simple-web-app&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=Crazy-pro_simple-web-app)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Crazy-pro_simple-web-app&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Crazy-pro_simple-web-app)
