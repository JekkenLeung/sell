package com.jekken.service;

import com.jekken.dto.OrderDTO;

/**
 * Create by Jekken
 * 2020/3/23 9:36
 */
public interface BuyerService {

    //查询一个订单
    OrderDTO findOrderOne(String openid,String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid,String orderId);
}
