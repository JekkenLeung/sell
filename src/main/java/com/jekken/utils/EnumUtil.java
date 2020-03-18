package com.jekken.utils;

import com.jekken.enums.CodeEnum;

/**
 * Create by Jekken
 * 2020/3/18 10:36
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code,Class<T> enumClass){
        for (T each:enumClass.getEnumConstants()){
            if (code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }

}
