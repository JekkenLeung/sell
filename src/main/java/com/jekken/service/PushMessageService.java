package com.jekken.service;

import com.jekken.dto.OrderDTO;

/**
 * 推动消息
 * Create by Jekken
 * 2020/3/23 11:01
 */
public interface PushMessageService {
    /**
     * 订单状态变更
     */
    void orderStatus(OrderDTO orderDTO);
}
