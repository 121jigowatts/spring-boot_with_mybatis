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

## MavenによるMyBatis Generatorの実行

mapperとentityを自動生成する。overwireteパラメータで既存のJavaファイルを上書き出力できる。

```sh
./mvnw -Dmybatis.generator.overwrite=true mybatis-generator:generate
```

`pom.xml`に記載された下記箇所をコメントアウトすることで`./mvnw install`により随時実行されるようにすることも可能。

```xml
<executions>
  <execution>
    <id>Generate MyBatis Artifacts</id>
    <goals>
      <goal>generate</goal>
    </goals>
    <configuration>
      <overwrite>true</overwrite>
    </configuration>
  </execution>
</executions>
```

## SonarQubeによるコード静的解析

### Dockerによる環境構築

```sh
mkdir sonar
cd sonar
vim docker-compose.yml
```

GitHubの[docker-compose.yml](https://github.com/SonarSource/docker-sonarqube/blob/master/example-compose-files/sq-with-postgres/docker-compose.yml)を参考に用意してコンテナ起動。

```sh
docker-compose up -d
```

[http://localhost:9000](http://localhost:9000)にアクセスし、プロジェクトを作成。ProjectKeyとTokenを取得。Tokenは環境変数に設定しておく。

```sh
#  coverageレポート作成
./mvnw clean \
 org.jacoco:jacoco-maven-plugin:prepare-agent install \
 -Dmaven.test.failure.ignore=true \
 test \
 org.jacoco:jacoco-maven-plugin:report

# コード解析の実行
./mvnw sonar:sonar \
  -Dsonar.projectKey=getStarted \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=$SONAR_QUBE_TOKEN_GET_STARTED
```
