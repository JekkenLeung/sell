package com.jekken.exception;

import com.jekken.enums.ResultEnum;
import lombok.Getter;

/**
 * Create by Jekken
 * 2020/3/17 14:36
 */
@Getter
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code,String message){
        super(message);
        this.code = code;
    }


}
