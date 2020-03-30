//package com.jekken.service.impl;
//
//import com.jekken.dto.OrderDTO;
//import com.jekken.enums.ResultEnum;
//import com.jekken.exception.SellException;
//import com.jekken.service.OrderService;
//import com.jekken.service.PayService;
//import com.jekken.utils.MathUtil;
//import com.lly835.bestpay.enums.BestPayTypeEnum;
//import com.lly835.bestpay.model.PayRequest;
//import com.lly835.bestpay.model.PayResponse;
//import com.lly835.bestpay.model.RefundRequest;
//import com.lly835.bestpay.model.RefundResponse;
//import com.lly835.bestpay.service.BestPayService;
//import com.lly835.bestpay.utils.JsonUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * Create by Jekken
// * 2020/3/24 9:23
// */
//@Service
//@Slf4j
//public class PayServiceImpl implements PayService {
//
//    private static final String ORDER_NAME = "微信点餐订单";
//
//    @Autowired
//    private BestPayService bestPayService;
//
//    @Autowired
//    private OrderService orderService;
//
//    @Override
//    public PayResponse create(OrderDTO orderDTO) {
//
//        PayRequest payRequest = new PayRequest();
//        payRequest.setOpenid(orderDTO.getBuyerOpenid());
//        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
//        payRequest.setOrderId(orderDTO.getOrderId());
//        payRequest.setOrderName(ORDER_NAME);
//        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_MP);
//        log.info("【微信支付】发起支付,request={}", JsonUtil.toJson(payRequest));
//
//        PayResponse payResponse = bestPayService.pay(payRequest);
//        log.info("【微信支付】发起支付，response={}",JsonUtil.toJson(payResponse));
//
//        return payResponse;
//    }
//
//    @Override
//    public PayResponse notify(String notifyDate) {
//
//        //1.验证签名
//        //2.支付的状态
//        //3.支付金额
//        //4.支付人（下单人 == 支付人）
//        PayResponse payResponse = bestPayService.asyncNotify(notifyDate);
//        log.info("【微信支付】异步通知，payResponse={}",JsonUtil.toJson(payResponse));
//
//        //查询订单
//        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());
//        if (orderDTO == null){
//            log.error("【微信支付】异步通知,订单不存在,orderId={}",payResponse.getOrderId());
//            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
//        }
//
//        //判断金额是否一致
//        if (!MathUtil.equals(payResponse.getOrderAmount(),orderDTO.getOrderAmount().doubleValue())){
//            log.error("【微信支付】异步通知,订单金额不一致,orderId={},微信通知金额={}，系统金额={}",payResponse.getOrderId(),payResponse.getOrderAmount(),orderDTO.getOrderAmount());
//            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
//        }
//
//        //修改订单状态
//        orderService.paid(orderDTO);
//
//
//        return payResponse;
//    }
//
//    @Override
//    public RefundResponse refund(OrderDTO orderDTO) {
//
//        RefundRequest refundRequest = new RefundRequest();
//        refundRequest.setOrderId(orderDTO.getOrderId());
//        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
//        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_MP);
//        log.info("【微信退款】request={}",JsonUtil.toJson(refundRequest));
//
//        RefundResponse refundResponse = bestPayService.refund(refundRequest);
//        log.info("【微信退款】response={}",JsonUtil.toJson(refundResponse));
//
//        return refundResponse;
//    }
//}
