package com.jekken.dao;

import com.jekken.pojo.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Create by Jekken
 * 2020/3/23 9:21
 */
public interface OrderMasterDao extends JpaRepository<OrderMaster ,String> {
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
