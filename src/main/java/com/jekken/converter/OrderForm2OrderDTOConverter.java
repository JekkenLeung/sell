package com.jekken.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jekken.dto.OrderDTO;
import com.jekken.form.OrderForm;
import com.jekken.pojo.OrderDetail;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Jekken
 * 2020/3/25 21:48
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();

        try {
            gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());

        }catch (Exception e){
            log.error("【对象转换】错误,string = {}",orderForm.getItems());
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

}
