# コマンドが一つでもエラーになったらシェルを終了する
set -e

# psql -U <USER_NAME> <DB_NAME>
psql -U admin admin << EOSQL
CREATE TABLE IF NOT EXISTS messages (
  id          SERIAL PRIMARY KEY,
  text        VARCHAR(255),
  jsonb_value JSONB
);

INSERT INTO messages (
  text,
  jsonb_value
) VALUES (
  'hello postgres',
  json_build_object('key', 1, 'database_name', 'PostgreSQL')
);

EOSQL