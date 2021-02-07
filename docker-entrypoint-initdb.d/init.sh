# コマンドが一つでもエラーになったらシェルを終了する
set -e

# psql -U <USER_NAME> <DB_NAME>
psql -U admin -d mydb << EOSQL
CREATE TABLE IF NOT EXISTS messages (
  id          SERIAL PRIMARY KEY,
  text        VARCHAR(255),
  jsonb_value JSONB
);

CREATE TABLE IF NOT EXISTS users (
  id          VARCHAR(32) PRIMARY KEY,
  name        VARCHAR(255)
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

CREATE TABLE IF NOT EXISTS orders (
  order_number          VARCHAR(20),
  order_detail_id       VARCHAR(20),
  PRIMARY KEY(order_number, order_detail_id)
);

CREATE TABLE IF NOT EXISTS order_details (
  order_detail_id       VARCHAR(20) PRIMARY KEY,
  quantity              int,
  unit_price            int,
  product_id            VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS products (
  product_id            VARCHAR(20) PRIMARY KEY,
  product_name          VARCHAR(20)
);

INSERT INTO users (id,name) VALUES ('1','Alice');

INSERT INTO products (product_id,product_name) VALUES ('prd01','productName01');
INSERT INTO products (product_id,product_name) VALUES ('prd02','productName02');

INSERT INTO order_details (order_detail_id,quantity,unit_price,product_id) VALUES ('detail001',1,100,'prd01');
INSERT INTO order_details (order_detail_id,quantity,unit_price,product_id) VALUES ('detail002',2,200,'prd02');

INSERT INTO orders (order_number,order_detail_id) VALUES ('oder001','detail001');
INSERT INTO orders (order_number,order_detail_id) VALUES ('oder001','detail002');

EOSQL