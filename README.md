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

To test the API :

1. 
PUT : 

```
URI : https://localhost:8443/user

Request Body : 
{"first_name":"anand","last_name":"velu","credential": {"user_name":"ananthvelu", "password":"pass", "email" :"ananthvelu@gmail.com"}}

Header : 
Authorization	Basic YWRtaW46c2VjcmV0
Accept	application/json
Content-Type	application/json
```

GET :

```
https://localhost:8443/user?firstname=anand&lastname=velu

```
