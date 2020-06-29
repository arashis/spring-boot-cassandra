# spring-boot-cassandra
This project is to use cassandra

# How to run
Please, set up cassandra table and data using cql.txt.
Edit resources/application.yml for your cassandra configuration.
Build
```$xslt
$ ./gradlew clean build
```
Run
```$xslt
$ java -jar build/libs/spring-boot-cassandra-0.0.1-SNAPSHOT.jar
```
Request
```$xslt
$ curl http://localhost:8080/book/1/1 | jq .
{
  "bookKey": {
    "bookId": 1,
    "bookVersion": 1
  },
  "title": "book title 1",
  "description": "book description 1",
  "authors": [
    {
      "name": "author name 1-1",
      "description": "author description 1-1"
    },
    {
      "name": "author name 1-2",
      "description": "author description 1-2"
    }
  ]
}
```
