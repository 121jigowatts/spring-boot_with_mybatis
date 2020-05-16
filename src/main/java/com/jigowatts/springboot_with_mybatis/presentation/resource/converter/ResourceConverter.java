package com.jigowatts.springboot_with_mybatis.presentation.resource.converter;

public interface ResourceConverter<R, E> {

    E toEntity(R resource);

    R toResource(E entity);
}