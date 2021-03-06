package com.jigowatts.springboot_with_mybatis.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Messages {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.messages.id
     *
     * @mbg.generated Sat May 16 13:04:10 JST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.messages.text
     *
     * @mbg.generated Sat May 16 13:04:10 JST 2020
     */
    private String text;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.messages.jsonb_value
     *
     * @mbg.generated Sat May 16 13:04:10 JST 2020
     */
    private Object jsonbValue;
}