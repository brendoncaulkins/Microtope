FROM mariadb

COPY sql-files/ /docker-entrypoint-initdb.d/

HEALTHCHECK --interval=15s --timeout=30s --start-period=25s --retries=5 CMD [ "/bin/bash", "-c","mysql -u docker_health -pshowmelove -e 'SELECT * FROM health' microtope" ]

CMD ["mysqld"]