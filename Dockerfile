FROM postgres:12-alpine
ENV LANG ja_JP.utf8

COPY docker-entrypoint-initdb.d/ /docker-entrypoint-initdb.d/
RUN chown postgres:postgres /docker-entrypoint-initdb.d/*.sh