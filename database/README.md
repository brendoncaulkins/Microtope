# Microworld - MariaDB

This Repo contains the sources to build the MariaDB required for the microworld example.

## How To

To build (in this repository):
`docker build . -t microtope/database:{CurrentVersion}`

To run:
`docker run -e MYSQL_RANDOM_ROOT_PASSWORD=yes -e MYSQL_INITDB_SKIP_TZINFO=true microtope/database:{CurrentVersion}`

Optional:
`docker run -e MYSQL_ROOT_PASSWORD={SecretPW} -e MYSQL_INITDB_SKIP_TZINFO=true microtope/database:{CurrentVersion}`

## Change

If you want any changes done to the Image, add .sql files to the folder. They are run in alphabetical order.

You can also add .sh files to this folder.

**Do not alter or add any entrypoint scripts in docker, as the mariadb scripts will be overwritten and the container rendered useless!**

## Validate

To validate the database, do

```Shell
root@host>docker exec -it {YourMariaContainer} /bin/bash
root@container> su mysql
$> mysql -u admin -p
$> enter password: 
admin[(none)] > USE microtope;
admin[(Microworld)] > show tables;
```

You can now select and join and do everything you want with your database.

## Using Timewalking Queries

From now on the database supports system-versioned queries. All of the base tables have system-versioning.

To access the data as of a certain period of time, you can do a query like this:

`SELECT player_id, player_name FROM players FOR SYSTEM_TIME AS OF ('2019-09-21 07:27:00');`

A simple select statement will return the latest valid data.

For more information, see [The MariaDB-Dokumentation on Temporal Databases](https://mariadb.com/kb/en/library/temporal-data-tables/)

## ToDo

- Smarter storing of passwords for user-generation
- Timezone is maybe required (My Docker-Image is 2 Hours behind my real-time in germany)
- Timewalking Views

## Sources

- [MariaDB Image](https://hub.docker.com/r/mariadb/server/)
- [MariaDB KnowledgeBase](https://mariadb.com/kb/en/)

## Troubleshooting

- First try the validate
- Run the container without the -d flag, or see `docker logs {your failed container}`
- Make sure to follow the instructions given in "grantPrivileges.sql" so you dont have issues with any access
