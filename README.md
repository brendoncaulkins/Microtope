# AMQ-Reciver

This component takes simple messages from a given ActiveMQ.
It is long running and sleeps for n-ms before it looks for new messages. 
The messages get processed and safed into an database.
Part of the microworld-miniproject.

## To Build

- first, make `mvn clean package` and recognize the version number
- alter the Dockerfile to use the new version
- run `sudo docker build . -t amqreciever:{nextversion}`

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
