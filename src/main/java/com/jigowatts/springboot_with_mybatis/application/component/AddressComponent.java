package com.jigowatts.springboot_with_mybatis.application.component;

import com.jigowatts.springboot_with_mybatis.domain.model.address.Address;

public interface AddressComponent {
    Address getBy(String id);
}
