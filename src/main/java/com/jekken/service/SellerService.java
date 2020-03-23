package com.jekken.service;

import com.jekken.pojo.SellerInfo;

/**
 * 卖家端service
 * Create by Jekken
 * 2020/3/23 15:27
 */
public interface SellerService {

    /**
     * 通过openid查询卖家端信息
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
