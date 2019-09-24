FROM mariadb:10.3

ENV MYSQL_ROOT_PASSWORD=my-secret-pw \
    MYSQL_USER=tarent \
    MYSQL_PASSWORD=tarent \
    MYSQL_DATABASE=ta_youtrain

HEALTHCHECK --interval=1s --timeout=1s --retries=15 \
  CMD /usr/bin/mysql --user=tarent --password=tarent --execute "SHOW DATABASES;"