package com.jekken.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Create by Jekken
 * 2020/4/2 0:23
 */
@Data
public class UpdateAdminPasswordParam {

    @NotEmpty(message = "用户名不能为空")
    private String username;
    @NotEmpty(message = "旧密码不能为空")
    private String oldPassword;
    @NotEmpty(message = "新密码不能为空")
    private String newPassword;

}
