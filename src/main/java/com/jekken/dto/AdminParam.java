package com.jekken.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * 用户登录参数
 * Create by Jekken
 * 2020/4/1 22:50
 */
@Data
public class AdminParam {

    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    private String password;

    /**
     * 用户头像
     */
    private String icon;

    /**
     * 邮箱
     */
    @Email(message ="邮箱格式不合法")
    private String email;
    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 备注
     */
    private String note;

}
