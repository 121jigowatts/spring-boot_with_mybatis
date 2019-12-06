package com.jigowatts.springboot_with_mybatis.mapper;

import java.util.List;

import com.jigowatts.springboot_with_mybatis.domain.Message;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * MessageMapper
 */
@Mapper
public interface MessageMapper {
    @Select("SELECT text FROM messages ORDER BY id")
    List<Message> findAll();
    
    @Insert("INSERT INTO messages(text) VALUES(#{text})")
    int create(Message message);
}