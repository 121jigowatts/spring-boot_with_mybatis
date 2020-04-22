CREATE TABLE IF NOT EXISTS messages (
  id          SERIAL PRIMARY KEY,
  text        VARCHAR(255),
  jsonb_value JSON
);

CREATE TABLE IF NOT EXISTS users (
  id          VARCHAR(32) PRIMARY KEY,
  name        VARCHAR(255)
);

-- h2ではJsonb型はサポートされていないため、Json型でテスト
-- 登録時はFORMAT JSONキーワードを付与
INSERT INTO messages (
  text, 
  jsonb_value
  ) VALUES (
    'hello postgres',
    '{"key":1,"database_name":"PostgreSQL","schemas":[{"schema_name":"schema01","tables":[{"table_name":"users","columns":[{"column_name":"id","sort_order":1},{"column_name":"name","sort_order":2},{"column_name":"age","sort_order":3}]},{"table_name":"products","columns":[{"column_name":"id","sort_order":1},{"column_name":"name","sort_order":2},{"column_name":"price","sort_order":3}]}]}]}' FORMAT JSON
);

INSERT INTO users (id,name) VALUES ('1','Alice');
