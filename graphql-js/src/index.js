const { GraphQLServer } = require("graphql-yoga");
const { prisma } = require("./generated/prisma-client");

let links = [
  {
    id: "link-0",
    url: "rharshad.com",
    description: "Tech blog"
  }
];

const resolvers = {
  Query: {
    info: () => `API of Hackernews Clone`,
    feed: (root, args, context, info) => {
      return context.prisma.links();
    }
  },
  Mutation: {
    post: (root, args, context) => {
      return context.prisma.createLink({
        url: args.url,
        description: args.description
      });
    }
  }
};

const server = new GraphQLServer({
  typeDefs: "./src/schema.graphql",
  resolvers,
  context: request => {
    return {
      ...request,
      prisma
    };
  }
});

server.start(() => console.log("Server is running at http://localhost:4000"));
