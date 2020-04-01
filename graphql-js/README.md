# graphql-js example

This App utilizes:

- [graphql-yoga](https://github.com/prisma-labs/graphql-yoga) for fully-featured GraphQL Server with focus on easy setup, performance & great developer experience

- [Prisma](https://www.prisma.io/) for Simplified & type-safe database access


## Pre-requisites

We are using [Prisma](https://www.prisma.io/) an ORM tool which provides a data access layer for resolving queries to database.

Here, we will be setting up a ``prisma server`` and ``mysql db`` with docker.

Run below command to start the services.

```bash
docker-compose up -d
```

Install prisma CLI:

```bash
yarn global add prisma
```

## Deploy Prisma Configuration

We then deploy the service configuration to prisma server.

```
prisma deploy
```

It will set up a new database and create the table fields.

Access prisma playground at http://localhost:4466/ where you can run your queries and access the schema.

Access prisma admin console at http://localhost:4466/_admin where you can run GraphQL queries and access the data in database.

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

## Start Server

Start the server using below command and access the GraphQL Playground at <http://localhost:4000>

```bash
node src/index.js
```

### Mutation

```graphql
mutation {
  post(url: "prisma.io", description: "Prisma replaces traditional ORMs"){
    id
  }
}
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

References:

<https://www.howtographql.com/graphql-js/0-introduction/>