# Spring-boot Banking
Example project demonstrating the use of Java and Spring-boot to build a microservice to be used by an online bank

## Running locally
```
./mvnw clean package
java -jar target/Banking-0.0.1.jar
```

## Running on Docker
```
docker build -t "spring-boot:banking" .
docker run -p 8080:8080 spring-boot:banking
```

## Testing
1. Import the Postman collection file into the application or copy the request body from there

### Extensions
1. Use of persisted database
2. Use of asynchronous programming backed by message queue for transactions
3. Others mentioned throughout the code