package com.jekken.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Create by Jekken
 * 2020/3/29 12:48
 */
@Data
public class ProductVO implements Serializable {


    private static final long serialVersionUID = 7325233728898381527L;

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;

}
