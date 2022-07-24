## Getting Started

```bash
npm install && npm start
```

## Queries

```bash
curl --request POST   --header 'content-type: application/json'   --url http://localhost:4000/   --data '{"query":"query { tracksForHome { id, title, author { id, name, photo }, thumbnail, length, modulesCount } }"}'
```