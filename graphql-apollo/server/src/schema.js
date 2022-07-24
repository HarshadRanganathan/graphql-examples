const { gql } = require('apollo-server');

const typeDefs = gql`
  type Query {
    "Query to get Tracks for homepage"
    tracksForHome: [Track!]!
  }

  "Track teaches about a specific topic"
  type Track {
    id: ID!
    "Track's title"
    title: String!
    "Track's main author"
    author: Author!
    "Track's main illustration"
    thumbnail: String
    "Track's approximate length"
    length: Int
    "number of modules in the Track"
    modulesCount: Int
  }

  "Author of a Track"
  type Author {
    id: ID!
    "Name of the author"
    name: String!
    "Photo of the author"
    photo: String
  }
`;

module.exports = typeDefs;