package com.jekken.pojo;

import com.jekken.enums.OrderStatusEnum;
import com.jekken.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Create by Jekken
 * 2020/3/18 15:00
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    @Id
    private String orderId;

    /**
     * 卖家名字
     */
    private String buyerName;

    /**
     * 卖家手机号
     */
    private String buyerPhone;

    /**
     * 卖家地址
     */
    private String buyerAddress;

    /**
     * 卖家微信openid
     */
    private String buyerOpenid;

    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态，默认0为新下单
     */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /**
     * 支付状态，默认0为未支付
     */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
