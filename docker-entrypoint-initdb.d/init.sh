# コマンドが一つでもエラーになったらシェルを終了する
set -e

# psql -U <USER_NAME> <DB_NAME>
psql -U admin admin << EOSQL
CREATE TABLE IF NOT EXISTS messages (
  id        SERIAL PRIMARY KEY,
  text      VARCHAR(255)
);

INSERT INTO messages(text) VALUES('hello postgres');

EOSQL