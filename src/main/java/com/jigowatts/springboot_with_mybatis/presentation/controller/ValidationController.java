package com.jigowatts.springboot_with_mybatis.presentation.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ValidationController
 */
@RestController
@Validated
public class ValidationController {

    @GetMapping(value = "/validation")
    public String getPerson(
            @Valid @NotNull @Pattern(regexp = "^[0-9a-zA-Z]*$") @RequestParam("productCode") String productCode,
            @Valid @NotBlank @Size(min = 1, max = 10) @RequestParam("productName") String productName,
            @Valid @NotNull @RequestParam("targetDay") Integer targetDay) {
        return String.format("プロダクト名：%s(プロダクトコード：%s)の入荷は %d 日後です", productName, productCode, targetDay);
    }
}