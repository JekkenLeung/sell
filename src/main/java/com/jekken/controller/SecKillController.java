package com.jekken.controller;

import com.jekken.service.SeckKillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by Jekken
 * 2020/3/29 13:56
 */
@RestController
@RequestMapping("/skill")
@Slf4j
public class SecKillController {
    @Autowired
    private SeckKillService seckKillService;

    /**
     * 查询秒杀活动特价商品
     */
    @GetMapping("/query/{productId}")
    public String query(@PathVariable String productId) {
        return seckKillService.querySecKillProductInfo(productId);
    }

    /**
     * 秒杀
     */
    @GetMapping("/order/{productId}")
    public String skill(@PathVariable String productId){
        log.info("@skill request,productId:"+productId);
        seckKillService.orderProductMockDiffUser(productId);
        return seckKillService.querySecKillProductInfo(productId);
    }


}
