FROM node:12.8.1-alpine

# Adding bash, as wait-for-it needs bash
RUN apk add --no-cache bash

ENV dir /usr/api

ENV MariaDB_Adress 127.0.0.1
ENV MariaDB_Port 3306
ENV MariaDB_DatabaseName microtope
ENV MariaDB_User api
ENV MariaDB_PW Need2Read

ENV Exposed_Port 8080

USER root
RUN mkdir -p $dir
WORKDIR $dir

COPY package.json $dir
RUN npm install

COPY . $dir

RUN npm run build

COPY ./utils/wait-for-it.sh $dir

ENTRYPOINT [ "/bin/bash", "-c","./wait-for-it.sh -t 0 -s --host=$MariaDB_Adress --port=$MariaDB_Port -- node . ${MariaDB_Adress} ${MariaDB_User} ${MariaDB_PW} ${MariaDB_Port} ${MariaDB_DatabaseName} ${Exposed_Port}"]