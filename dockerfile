FROM azul/zulu-openjdk-alpine:12.0.2

ENV dir /usr/app
ENV ActiveMQ_Adress 127.0.0.1
ENV ActiveMQ_Port 61616
ENV ActiveMQ_Queue game_queue
ENV ActiveMQ_User Artemis
ENV ActiveMQ_Pwd  Artemis

ENV MariaDB_Adress 127.0.0.1
ENV MariaDB_Port 3306
ENV MariaDB_User micro_writer
ENV MariaDB_PW INeed2Write

ENV SleepTimer 1000

RUN apk update && apk add bash

RUN mkdir -p $dir

COPY ./target/worker-0.1-jar-with-dependencies.jar $dir

WORKDIR $dir

ENTRYPOINT ["/bin/bash", "-c", "java -jar worker-0.1-jar-with-dependencies.jar $ActiveMQ_Adress $ActiveMQ_Port $ActiveMQ_Queue $ActiveMQ_User $ActiveMQ_Pwd $MariaDB_Adress $MariaDB_Port $MariaDB_User $MariaDB_PW $SleepTimer"]