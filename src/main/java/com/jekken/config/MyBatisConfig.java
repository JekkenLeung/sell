package com.jekken.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis配置类
 * Create by Jekken
 * 2020/4/1 22:42
 */
@Configuration
@MapperScan({"com.jekken.mapper","com.jekken.dao"})
public class MyBatisConfig {
}
