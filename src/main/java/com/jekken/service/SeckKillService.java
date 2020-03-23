package com.jekken.service;

/**
 * Create by Jekken
 * 2020/3/23 15:04
 */
public interface SeckKillService {

    /**
     * 查询秒杀活动特价商品的信息
     */
    String querySeckillProductInfo(String productId);

    /**
     * 模拟不同用户秒杀同意商品的请求
     */
    void orderProductMockDiffUser(String productId);

}
