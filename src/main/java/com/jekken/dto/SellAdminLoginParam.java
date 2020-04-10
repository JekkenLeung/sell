package com.jekken.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Create by Jekken
 * 2020/4/11 0:29
 */
@Data
public class SellAdminLoginParam {

    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty(message = "密码不能为空")
    private String password;



}
