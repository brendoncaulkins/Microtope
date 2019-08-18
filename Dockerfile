FROM node:12.8.1-alpine

ENV dir /usr/api

ENV MariaDB_Adress 127.0.0.1
ENV MariaDB_Port 3306
ENV MariaDB_DatabaseName microtope
ENV MariaDB_User api
ENV MariaDB_PW Need2Read

USER root
RUN mkdir -p $dir
WORKDIR $dir

COPY package.json $dir
RUN npm install

COPY . $dir

RUN npm build

EXPOSE 8080

CMD ["sh ","-c","node . ${DBMariaDB_Adress} ${MariaDB_User} ${MariaDB_PW} ${MariaDB_Port} ${MariaDB_DatabaseName}"]