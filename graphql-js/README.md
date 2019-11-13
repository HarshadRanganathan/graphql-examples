
## Pre-requisites

We are using [Prisma](https://www.prisma.io/) an ORM tool which provides us with a data access layer for resolving our queries to database.

Here, we will be setting up a prisma server and mysql db with docker.

Run below command to start the services.

```bash
docker-compose up -d
```

Access prisma playground at http://localhost:4466/ where you can run your queries and access the schema.

Access prisma admin console at http://localhost:4466/_admin where you can run GraphQL queries and access the database data.

Sample Prisma Server Query:

```graphql
{
  links {
    id
    description
    url
  }
}
```

## Deploy Prisma Configuration

We then deploy the service configuration to prisma server.

```
prisma deploy
```

It will set up a new database and create the table fields.

## Start Server

Start the server using below command and access the GraphQL Playground at <http://localhost:4000>

```bash
node src/index.js
```

### Query

```graphql
query {
  feed {
    id
    url
    description
  }
}
```

### Mutation

```graphql
mutation {
  post(url: "prisma.io", description: "Prisma replaces traditional ORMs"){
    id
  }
}
```

References:

<https://www.howtographql.com/graphql-js/0-introduction/>