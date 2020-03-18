package com.jekken.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jekken.enums.ProductStatusEnum;
import com.jekken.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Create by Jekken
 * 2020/3/17 10:11
 */
@Entity
@Data
@DynamicUpdate
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 6399186181668983148L;//反序列化

    @Id
    private String productId;
    /** 商品名称 */
    private String productName;
    /** 商品单价 */
    private BigDecimal productPrice;
    /** 商品库存*/
    private Integer productStock;
    /**
     * 描述
     */
    private String productDescription;
    /**
     * 小图
     */
    private String productIcon;

    /**
     * 状态，0正常1下架
     */
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    /**
     * 类目编号
     */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
    }

    /**
     * 拼接图片链接
     */
    public ProductInfo addImageHost(String host){
        if (productIcon.startsWith("//")||productIcon.startsWith("http")){
            return this;
        }
        if (!host.startsWith("http")){
            host = "//" +host;
        }
        if (!host.endsWith("/")){
            host = host + "/";
        }
        productIcon = host+productIcon;
        return this;
    }



}
