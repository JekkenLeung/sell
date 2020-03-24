package com.jekken.service.impl;

import com.jekken.dao.SellerInfoDao;
import com.jekken.pojo.SellerInfo;
import com.jekken.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by Jekken
 * 2020/3/23 17:01
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoDao sellerInfoDao;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoDao.findByOpenid(openid);
    }
}
