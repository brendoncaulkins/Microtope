# Worker

This component takes simple messages from a given ActiveMQ.
It is long running and sleeps for n-ms before it looks for new messages.

The messages get processed and safed into an database.
Part of the microworld-miniproject.

## To Build

- first, make `mvn clean package verify` and recognize the version number
- alter the Dockerfile to use the new version
- run `sudo docker build . -t microtope/worker:{nextversion}`

The `verify` in maven is very important! It checks for your coverage.

You maybe want to skip it and can even build your docker image with it - but the build pipeline wont be cheated :)

## ToDo

- Logging with Log4j2
- Automate Docker Build into Maven? (Currently, the missmatching versions suck a little)
- IntegrationTest

## Requirements

- Java 12
- Maven 3
- Docker 18
- The ActiveMQ Dockerfile from the other Microworld Project (But any ActiveMQ should work)

## TroubleShooting

- It always takes localhost! - Check whether your -e comes before the image name in docker run. This took me only 3 hours.
- It doesn't find my amq! - Check whether your AMQ is running with `docker ps` and inspect the ip with `docker inspect`
