Start the Spring Boot application and the API is available on http://localhost:8080/graphql

Curl:

````curl
curl -X POST \
  http://localhost:8080/graphql \
  -H 'Content-Type: application/json' \
  -d '{
    "query": "{ bookById(id: \"book-1\") { id name pageCount author { firstName lastName } } }"
}'
````

Postman GraphQL Query:

````postman
query {
    bookById(id: "book-1") { 
        id 
        name 
        pageCount 
        author { 
            firstName 
            lastName 
        } 
    } 
}
````

Sample Response:

````json
{
    "data": {
        "bookById": {
            "id": "book-1",
            "name": "Harry Potter and the Philosopher's Stone",
            "pageCount": 223,
            "author": {
                "firstName": "Joanne",
                "lastName": "Rowling"
            }
        }
    }
}
````

References: 

- https://www.graphql-java.com/tutorials/getting-started-with-spring-boot/