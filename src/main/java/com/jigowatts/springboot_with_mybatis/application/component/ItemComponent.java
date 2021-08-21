package com.jigowatts.springboot_with_mybatis.application.component;

import java.util.List;

import com.jigowatts.springboot_with_mybatis.domain.model.item.Item;

public interface ItemComponent {
    List<Item> getBy(String id);
}
