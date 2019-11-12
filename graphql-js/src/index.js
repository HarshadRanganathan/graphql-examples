const { GraphQLServer } = require('graphql-yoga')

let links = [
  {
    id: 'link-0',
    url: 'rharshad.com',
    description: 'Tech blog'
  }
]

let idCount = links.length

const resolvers = {
  Query: {
    info: () => `API of Hackernews Clone`,
    feed: () => links,
  },
  Mutation: {
    post: (parent, args) => {
      const link = {
        id: `link-${idCount++}`,
        url: args.url,
        description: args.description
      }
      links.push(link)
      return link
    }
  }
}

const server = new GraphQLServer({
  typeDefs: './src/schema.graphql',
  resolvers
})

server.start(() => console.log('Server is running at http://localhost:4000'));