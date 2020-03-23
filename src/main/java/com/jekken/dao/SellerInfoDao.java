package com.jekken.dao;

import com.jekken.pojo.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Create by Jekken
 * 2020/3/23 9:30
 */
public interface SellerInfoDao extends JpaRepository<SellerInfo,String> {

    SellerInfo findByOpenid(String openid);
}
