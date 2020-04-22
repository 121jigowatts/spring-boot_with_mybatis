package com.jigowatts.springboot_with_mybatis.infrastructure.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jigowatts.springboot_with_mybatis.domain.model.message.Column;
import com.jigowatts.springboot_with_mybatis.domain.model.message.Database;
import com.jigowatts.springboot_with_mybatis.domain.model.message.Message;
import com.jigowatts.springboot_with_mybatis.domain.model.message.MessageCriteria;
import com.jigowatts.springboot_with_mybatis.domain.model.message.Schema;
import com.jigowatts.springboot_with_mybatis.domain.model.message.Table;
import com.jigowatts.springboot_with_mybatis.util.json.JsonConverter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * MessageMapperTest
 */
// @MybatisTestを付与するとデフォルトで組み込みデータベースを使用する設定となる
@MybatisTest
// 組み込みデータベースを使いたくない場合は以下の設定を有効にする
// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MessageMapperTest {

    @Autowired
    MessageMapper messageMapper;
    private JsonConverter<Database> jsonConverter;
    private Database initData_database;

    @BeforeEach
    public void setup() {
        this.jsonConverter = new JsonConverter<Database>();
        // users
        List<Column> usersColumns = new ArrayList<>();
        usersColumns.add(new Column("id", 1));
        usersColumns.add(new Column("name", 2));
        usersColumns.add(new Column("age", 3));
        // products
        List<Column> productsColumns = new ArrayList<>();
        productsColumns.add(new Column("id", 1));
        productsColumns.add(new Column("name", 2));
        productsColumns.add(new Column("price", 3));

        List<Table> schema01Tables = new ArrayList<>();
        schema01Tables.add(new Table("users", usersColumns));
        schema01Tables.add(new Table("products", productsColumns));

        List<Schema> postgresSchemas = new ArrayList<>();
        postgresSchemas.add(new Schema("schema01", schema01Tables));
        this.initData_database = new Database(1, "PostgreSQL", postgresSchemas);
    }

    @Test
    public void findOneTest() throws IOException {
        Message expected = Message.builder().id(1).text("hello postgres").build();
        Database expected_db = this.initData_database;
        Schema expected_schema = expected_db.getSchemas().get(0);
        Table expected_table = expected_schema.getTables().get(0);
        Column expected_column = expected_table.getColumns().get(0);
        Message actual = messageMapper.findOne(1);

        assertThat(actual.getId()).isEqualTo(expected.getId());
        assertThat(actual.getText()).isEqualTo(expected.getText());

        Database actual_db = jsonConverter.convertToObject(actual.getJsonbValue(),
        Database.class);
        assertThat(actual_db.getKey()).isEqualTo(expected_db.getKey());
        assertThat(actual_db.getDatabaseName()).isEqualTo(expected_db.getDatabaseName());
        Schema actual_schema = actual_db.getSchemas().get(0);
        assertThat(actual_schema.getSchemaName()).isEqualTo(expected_schema.getSchemaName());
        Table actual_table = actual_schema.getTables().get(0);
        assertThat(actual_table.getTableName()).isEqualTo(expected_table.getTableName());
        Column actual_column = actual_table.getColumns().get(0);
        assertThat(actual_column.getColumnName()).isEqualTo(expected_column.getColumnName());
        assertThat(actual_column.getSortOrder()).isEqualTo(expected_column.getSortOrder());
    }

    @Test
    public void countTest() {
        long expected = 1L;
        long actual = messageMapper.count();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @Sql(scripts = "/com/jigowatts/springboot_with_mybatis/infrastructure/repository/MessageMapperTest.sql")
    public void findAllTest() {
        List<Message> expected = new ArrayList<Message>();

        Message message01 = Message.builder().id(1).text("hello").build();
        expected.add(message01);
        Message message02 = Message.builder().id(2).text("hoge").build();
        expected.add(message02);
        Message message03 = Message.builder().id(3).text("hana").build();
        expected.add(message03);
        Message message04 = Message.builder().id(4).text("honey").build();
        expected.add(message04);

        MessageCriteria criteria = MessageCriteria.builder().text("h").build();
        List<Message> actual = messageMapper.findAllByCriteria(criteria);
        assertThat(actual.size()).isEqualTo(expected.size());
    }

    @Test
    public void createTest() {
        String jsonbValue = "{}";
        try {
            jsonbValue = jsonConverter.convertToString(initData_database);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            fail();
        }
        Message message = Message.builder().text("fizz").jsonbValue(jsonbValue).build();
        messageMapper.create(message);

        MessageCriteria criteria = MessageCriteria.builder().text("fizz").build();
        List<Message> actual = messageMapper.findAllByCriteria(criteria);

        assertThat(actual.size()).isEqualTo(1);

        assertThat(actual.get(0).getText()).isEqualTo("fizz");

    }

    @Test
    public void updateTest() {
        Message message = Message.builder().id(1).text("fizzbuzz").jsonbValue("{}").build();
        boolean actual = messageMapper.update(message);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("Message削除のテスト")
    public void deleteTest() {
        boolean actual = messageMapper.delete(1);

        assertThat(actual).isTrue();
    }
}