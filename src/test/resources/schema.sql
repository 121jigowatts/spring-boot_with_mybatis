CREATE TABLE IF NOT EXISTS messages (
  id          SERIAL PRIMARY KEY,
  text        VARCHAR(255),
  jsonb_value JSON
);

CREATE TABLE IF NOT EXISTS users (
  id          VARCHAR(32) PRIMARY KEY,
  name        VARCHAR(255)
);

INSERT INTO messages (text) VALUES ('hello postgres');

-- INSERT INTO messages (
--   text,
--   jsonb_value
-- ) VALUES (
--   'hello postgres',
--   json_build_object('key', 1, 'database_name', 'PostgreSQL',
--     'schemas', json_build_array(json_build_object('schema_name', 'schema01',
--       'tables', json_build_array(
--           json_build_object('table_name','users','columns', json_build_array(json_build_object('column_name','id','sort_order',1),json_build_object('column_name','name','sort_order',2),json_build_object('column_name','age','sort_order',3))),
--           json_build_object('table_name','products','columns',json_build_array(json_build_object('column_name','id','sort_order',1),json_build_object('column_name','name','sort_order',2),json_build_object('column_name','price','sort_order',3)))
--         )
--       )
--     )
--   )
-- );

INSERT INTO users (id,name) VALUES ('1','Alice');
-- INSERT INTO messages(text) VALUES('hello');