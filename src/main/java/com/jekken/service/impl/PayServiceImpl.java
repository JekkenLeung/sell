package com.jekken.service.impl;

import com.jekken.dto.OrderDTO;
import com.jekken.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.BestPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by Jekken
 * 2020/3/24 9:23
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private static final String ORDER_NAME = "微信点餐订单";


    @Override
    public PayResponse create(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public PayResponse notify(String notifyDate) {
        return null;
    }

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        return null;
    }
}
