# Microtope

Hey there! This is my try on a multi-language microservice architecture!
Hope you enjoy your stay. Feel free to contribute to any language and project fit for you.

I also would love someone who just runs the project on his docker and opens issues wherever he finds some.
I promise! the bad words you'll hear are just a way to express my happyness.

## Architecture

The Application currently is quite stringend:

1. A Java Application creates messages and puts it into an ActiveMQ-Artemis
2. An ActiveMQ Image is hosted
3. Another Java Application Reads messages from an ActiveMQ-Artemis and inserts them into an Mariadb
4. The Mariadb holds data and checks for integrity
5. An API is able to read the mariadb on demand and extract data as JSON-Documents
6. An UI is able to display and interact with the data granted from the API

A simple image on the architecture is following up soon(TM). I Promise!

## How to run

To run the Application, look into [the meta folder](./meta). This folder will contain any instructions required to run it on docker-compose or kubernetes.

## Licence

I'm using the [MIT Licence](./LICENCE). Therefore, feel free to use anything you like here.

## Contributing

I'll add a Contribution Guideline shortly! Until then, you're perfect if you make some changes and the docker-compose is starting.

If that is the case, open up a pull request and once it passes my azure-pipeline there is little reason to deny your changes.

Also I'm always happy about bugs and idea-issues.

## Preview Image

The Preview Image was taken from [Iconfinder](https://www.iconfinder.com/icons/3285297/andromeda_astronomy_cosmos_galaxy_space_spiral_universe_icon) in respect to the [Creative-Commons 3 Licence](https://creativecommons.org/licenses/by/3.0/). If you're the artist and ever encounter this repository, please reach out to me and maybe we can drink a coffee!
