package com.jekken.service.impl;

import com.jekken.config.WechatAccountConfig;
import com.jekken.dto.OrderDTO;
import com.jekken.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Create by Jekken
 * 2020/3/26 23:37
 */
@Slf4j
@Service
public class PushMessageServiceImpl implements PushMessageService {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig accountConfig;

    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId(accountConfig.getTemplateId().get("orderStatus"));
        templateMessage.setToUser(orderDTO.getBuyerOpenid());

        List<WxMpTemplateData> data = Arrays.asList(
          new WxMpTemplateData("first","亲，请记得收获噢"),
          new WxMpTemplateData("keyword1","微信点餐"),
          new WxMpTemplateData("keyword2","13726018866"),
          new WxMpTemplateData("keyword3",orderDTO.getOrderId()),
          new WxMpTemplateData("keyword4",orderDTO.getOrderStatusEnum().getMessage()),
          new WxMpTemplateData("keyword5","￥"+orderDTO.getOrderAmount()),
          new WxMpTemplateData("remark","欢迎再次光临！")
        );

        templateMessage.setData(data);

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        }catch (WxErrorException e){
            log.error("【微信模版消息】发送失败,{}",e);
        }


    }
}
