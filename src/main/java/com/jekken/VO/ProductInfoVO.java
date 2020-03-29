package com.jekken.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Create by Jekken
 * 2020/3/29 12:45
 */
@Data
public class ProductInfoVO implements Serializable {
    private static final long serialVersionUID = 595607257545704684L;

    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;



}
