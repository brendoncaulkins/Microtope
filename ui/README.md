<<<<<<< HEAD
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

If you have a database connected (and running) you can visit localhost:8080/api/healthcheck and see a status - otherwise you will log the according error.

## Build with

- Docker 18
- NPM 3.5.2
- Node 8.10 (12.8.1 in the Docker Container!)
- Typescript 3.5
- [Wait-for-it](https://github.com/vishnubob/wait-for-it)
=======
# Ui

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 8.2.2.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).
>>>>>>> ui/master
