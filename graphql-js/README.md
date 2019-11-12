
Start the server using below command and access the GraphQL Playground at <http://localhost:4000>

```bash
node src/index.js
```

## Query

```graphql
query {
  feed {
    id
    url
    description
  }
}
```

## Mutation

```graphql
mutation {
  post(url: "prisma.io", description: "Prisma replaces traditional ORMs"){
    id
  }
}
```

References:

<https://www.howtographql.com/graphql-js/0-introduction/>