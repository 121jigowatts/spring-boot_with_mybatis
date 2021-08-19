package com.jigowatts.springboot_with_mybatis.application.service.helper;

import com.jigowatts.springboot_with_mybatis.domain.model.address.Address;

public class AddressHelper {
    public static Address address_id_1() {
        return Address.builder().id("1").zip("12345").street("f.street").city("sand").country("EOL").build();
    }
}
