package com.jigowatts.springboot_with_mybatis.application.component;

import java.util.List;

import com.jigowatts.springboot_with_mybatis.domain.model.item.Item;

import org.springframework.stereotype.Component;

@Component
public class ItemComponentIml implements ItemComponent {

    @Override
    public List<Item> getBy(String id) {
        // TODO Auto-generated method stub
        return List.of();
    }
    
}
