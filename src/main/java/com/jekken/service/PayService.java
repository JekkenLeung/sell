package com.jekken.service;

import com.jekken.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

/**
 * Create by Jekken
 * 2020/3/23 10:29
 */
public interface PayService {

    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifyDate);

    RefundResponse refund(OrderDTO orderDTO);


}
