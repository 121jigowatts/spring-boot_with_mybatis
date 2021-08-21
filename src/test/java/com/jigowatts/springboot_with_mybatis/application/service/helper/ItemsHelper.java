package com.jigowatts.springboot_with_mybatis.application.service.helper;

import java.util.List;

import com.jigowatts.springboot_with_mybatis.domain.model.item.Bag;
import com.jigowatts.springboot_with_mybatis.domain.model.item.Glasses;
import com.jigowatts.springboot_with_mybatis.domain.model.item.Item;

public class ItemsHelper {
    public static List<Item> items_id_1() {
        return List.of(Glasses.builder().id("1").lens("blue").build(), Bag.builder().id("2").capacity("L").build());
    }
    public static List<Item> items_id_2() {
        return List.of(Glasses.builder().id("3").lens("red").build());
    }
}
