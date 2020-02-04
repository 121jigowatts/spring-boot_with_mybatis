# Spring Boot with MyBatis

Demo project for Spring Boot with MyBatis.

![Java CI](https://github.com/121jigowatts/spring-boot_with_mybatis/workflows/Java%20CI/badge.svg)

## DockerコンテナによるPostgreSQL環境構築

### Dockerコンテナの生成 / 起動

```sh
# -dオプションを付けることでバックグラウンドで実行する
docker-compose up -d
```

### Dockerコンテナの一覧表示

```sh
docker-compose ps
```

### bashからpsqlを起動

```sh
# サービス名を指定してbash起動
docker-compose exec db /bin/bash
# psqlコマンド実行
psql -U admin
```

### Dockerコンテナの停止

```sh
docker-compose stop
```

### Dockerコンテナの停止 / 削除

```sh
docker-compose down
```
