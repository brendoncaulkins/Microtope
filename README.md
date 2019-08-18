# Microtope - API

This repository contains the files for the microtope api.

It reads from the database and returns json objects in a REST-way.

## How to Build and run

### With docker

```Shell
$> docker build . -t microtope/api:{anyversion}
$> docker run -p 8080:8080 microtope/api:{anyversion}
```

### Without docker

`$> npm run start`

### Verify

You should now be able to access your api in a browser with localhost:8080 and see a hello world.

## Build with

- Docker 18
- NPM 3.5.2
- Node 8.10 (12.8.1 in the Docker Container!)
- Typescript 3.5
