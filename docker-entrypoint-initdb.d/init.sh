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
  json_build_object('key', 1, 'database_name', 'PostgreSQL',
    'schemas', json_build_array(json_build_object('schema_name', 'schema01',
      'tables', json_build_array(
          json_build_object('table_name','users','columns', json_build_array(json_build_object('column_name','id','sort_order',1),json_build_object('column_name','name','sort_order',2),json_build_object('column_name','age','sort_order',3))),
          json_build_object('table_name','products','columns',json_build_array(json_build_object('column_name','id','sort_order',1),json_build_object('column_name','name','sort_order',2),json_build_object('column_name','price','sort_order',3)))
        )
      )
    )
  )
);

EOSQL