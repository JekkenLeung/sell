package com.jekken.service.impl;

import com.jekken.component.RedisLock;
import com.jekken.exception.SellException;
import com.jekken.service.ProductService;
import com.jekken.service.SeckKillService;
import com.jekken.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by Jekken
 * 2020/3/23 17:13
 */
@Service
public class SecKillServiceImpl implements SeckKillService {

    private static final int TIMEOUT = 10*1000;//超时时间10s

    @Autowired
    private RedisLock redisLock;


    /**
     *国庆活动，皮蛋粥特价，限量100000份
     */
    static Map<String,Integer> products;
    static Map<String,Integer> stock;
    static Map<String,String> orders;
    static {
        /**
         * 模拟多个表，商品信息表，库存表，秒杀成功订单表
         */
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();
        products.put("6666",100000);
        stock.put("6666",100000);
    }


    private String queryMap(String productId)
    {
        return "国庆活动，皮蛋粥特价，限量份"
                + products.get(productId)
                +" 还剩：" + stock.get(productId)+" 份"
                +" 该商品成功下单用户数目："
                +  orders.size() +" 人" ;
    }



    @Override
    public String querySecKillProductInfo(String productId) {
        return this.queryMap(productId);
    }

    @Override
    public void orderProductMockDiffUser(String productId) {
        //加锁
        String timeout =  String.valueOf(System.currentTimeMillis()+TIMEOUT);
        redisLock.lock("secKill",timeout);

        //查询改商品库存，为0则活动结束
        int stockNum = stock.get(productId);

        if (stockNum == 0){
            throw new SellException(100,"活动结束");
        }else {
            //下单 模拟不同openid
            orders.put(KeyUtil.genUniqueKey(),productId);
            //减库存
            stockNum = stockNum -1;
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();;
            }
            stock.put(productId,stockNum);
        }
        //解锁
        redisLock.unlock("secKill",timeout);
    }
}
