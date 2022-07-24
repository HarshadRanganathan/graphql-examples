const { ApolloServer } = require('apollo-server');
const typeDefs = require('./schema');

const mocks = {
  Query: () => ({
    tracksForHome: () => [...new Array(6)], // return data mocked upto 6 entries
  }),
  Track: () => ({
    id: () => 'Track 1',
    title: () => 'GraphQL Tutorial',
    author: () => {
      return {
        name: 'Harshad Ranganathan',
        photo: 'https://rharshad.com'
      };
    },
    thumbnail: () => 'https://rharshad.com',
    length: () => 1210,
    modulesCount: () => 6,
  }),
};

const server = new ApolloServer({ typeDefs, mocks });

server.listen().then(() => {
  console.log(`
    Server is running!
    Listening on port 4000
    Query at http://localhost: 4000
  `)
});