
package com.jigowatts.springboot_with_mybatis.domain.model.message;

import java.util.List;

public interface MessageRepository {
    Message findOne(int id);

    long count();

    List<Message> findAllByCriteria(MessageCriteria criteria);

    void create(Message message);

    boolean update(Message message);

    boolean delete(int id);
}