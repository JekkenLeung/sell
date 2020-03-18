package com.jekken.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Create by Jekken
 * 2020/3/18 14:47
 */
@Data
@Entity
public class SellerInfo {
    @Id
    private String sellerId;

    private String username;

    private String password;

    private String openid;
}
