package com.jekken.enums;

import lombok.Getter;

/**
 * 商品状态
 * Create by Jekken
 * 2020/3/17 10:59
 */
@Getter
public enum ProductStatusEnum implements CodeEnum{
    UP(0,"上架"),
    DOWN(1,"下架");

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
