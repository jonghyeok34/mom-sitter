FROM mysql:8


# ENV MYSQL_DATABASE=MOMSITTER \
#     MYSQL_ROOT_PASSWORD=my-secret-pw

ADD ddl.sql /docker-entrypoint-initdb.d

EXPOSE 3306