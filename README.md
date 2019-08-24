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

```
root@host>docker exec -it {YourMariaContainer} /bin/bash
root@container> su mysql
$> mysql -u admin -p
$> enter password: 
admin[(none)] > USE microtope;
admin[(Microworld)] > show tables;
```

You can now select and join and do everything you want with your database.

## ToDo

- Smarter storing of passwords for user-generation
- Some kind of additional Healthcheck?
- Auto-Versioning and timewalking
- Timezone is maybe required

## Sources

- [MariaDB Image](https://hub.docker.com/r/mariadb/server/)
- [MariaDB KnowledgeBase](https://mariadb.com/kb/en/)

## Troubleshooting

- First try the validate
- Run the container without the -d flag, or see `docker logs {your failed container}`
- Make sure to follow the instructions given in "grantPrivileges.sql" so you dont have issues with any access
