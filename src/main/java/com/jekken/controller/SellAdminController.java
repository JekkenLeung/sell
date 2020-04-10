package com.jekken.controller;

import com.jekken.VO.ResultVO;
import com.jekken.dto.SellAdminLoginParam;
import com.jekken.enums.ResultEnum;
import com.jekken.exception.SellException;
import com.jekken.service.SellAdminService;
import com.jekken.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by Jekken
 * 2020/4/11 0:03
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class SellAdminController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private SellAdminService adminService;


    @PostMapping("/login")
    public ResultVO login(@RequestBody SellAdminLoginParam sellAdminLoginParam, BindingResult result){
        if (result.hasErrors()){
            log.error("【登录账号】参数不正确,orderForm={}",sellAdminLoginParam);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),result.getFieldError().getDefaultMessage());
        }
        String token = adminService.login(sellAdminLoginParam.getUsername(),sellAdminLoginParam.getPassword());
        if (token ==null){
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL.getCode(),ResultEnum.LOGIN_FAIL.getMessage());
        }
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return ResultVOUtil.success(tokenMap);

    }



}
