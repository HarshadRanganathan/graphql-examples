Start the Spring Boot application and the API is available at http://localhost:8080/graphql

### Curl

````curl
curl -X POST \
  http://localhost:8080/graphql \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Content-Type: application/json' \
  -d '{
    "query": "query Books($id: ID!) { bookById(id: $id) { id title pageCount author { firstName lastName } } }",
    "variables": {  "id": "book-1"  }
}'
````

### Postman POST Request GraphQL Query

````graphql
query Books($id: ID!) {
    bookById(id: $id) { 
        id 
        title 
        pageCount 
        author { 
            firstName 
            lastName 
        } 
    } 
}
````

Variables:

````json
{
	"id": "book-1"
}
````

### Response

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

### Schema Introspection

Postman GraphQL Query:

````graphql
{
  __type(name: "Book") {
    name
    fields {
      name
      type {
        name
        kind
        ofType {
          name
          kind
        }
      }
    }
  }
}
````

References: 

- https://www.graphql-java.com/tutorials/getting-started-with-spring-boot/