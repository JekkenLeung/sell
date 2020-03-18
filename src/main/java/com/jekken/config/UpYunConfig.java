package com.jekken.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Create by Jekken
 * 2020/3/18 9:55
 */
@Component
@ConfigurationProperties("upyun")
@Data
public class UpYunConfig {

    private String bucketName;

    private String username;

    private String password;

    private String imageHost;



}
