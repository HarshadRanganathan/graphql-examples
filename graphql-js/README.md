# graphql-js example

```
Lot of the `graphql-yoga` features are now available as part of Apollo GraphQL. 

https://github.com/prisma-labs/graphql-yoga/issues/382#issuecomment-408390953
```

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

## Start Server

Start the server using below command and access the GraphQL Playground at <http://localhost:4000>

```bash
node src/index.js
```

## Operations

Run below queries in the graphql playground available at http://localhost:4000/

### Signup

Create a new user with below signup request which will return a token.

```graphql
mutation {
  signup(
    name: "Alice"
    email: "alice@prisma.io"
    password: "graphql"
  ) {
    token
    user {
      id
    }
  }
}
```

Configure this token in HTTP headers pane for all subsequent mutation/query operations.

```json
{
  "Authorization": "Bearer __TOKEN__"
}
```

### Validate Login

Check if the user login works by sending below request which will send a token response.

```graphql
mutation {
  login(
    email: "alice@prisma.io"
    password: "graphql"
  ) {
    token
    user {
      email
      links {
        url
        description
      }
    }
  }
}
```

### New Post

Create a new post by sending below request.

```graphql
mutation {
  post(
    url: "www.graphqlconf.org"
    description: "An awesome GraphQL conference"
  ) {
    id
  }
}
```

### Query Feeds

You can get all the posts created with below query:

```graphql
query{
  feed{
    description
    url
    postedBy{
      name
      email
      links {
        description
        url
      }
    }
  }
}
```

### Feeds Subscription

Subscribe to new links by running below query:

```graphql
subscription {
  newLink {
      id
      url
      description
      postedBy {
        id
        name
        email
      }
  }
}
```

### Vote Subscription

Subscribe to new votes.


```graphql
subscription {
  newVote {
    id
    link {
      url
      description
    }
    user {
      name
      email
    }
  }
}
```

Test it by adding a new vote.

```graphql
mutation {
  vote(linkId: "__LINK_ID__") {
    link {
      url
      description
    }
    user {
      name
      email
    }
  }
}
```

References:

<https://www.howtographql.com/graphql-js/0-introduction/>
