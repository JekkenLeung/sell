package com.jekken.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * Create by Jekken
 * 2020/3/17 14:44
 */
@Data
public class ResultVO<T> implements Serializable {


    private static final long serialVersionUID = -6922952163730655878L;

    private Integer code;

    private String msg;

    private T data;

}
