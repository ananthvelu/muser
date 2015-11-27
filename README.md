There are two ways to run this service :

#METHOD 1 :

1. compile and generate the war file

``` mvn clean install -Prelease```

2. Run the war using this command :

``` java -jar api/target/api-0.1-SNAPSHOT.war ```

This will use the embedded tomcat container to run the service.

#METHOD 2 :

1. do mvn package which will generate the war file.
2. go to api folder and then mvn spring-boot:run

In both above methods, **you need to use authorization as admin/secret to get a response from the service**
