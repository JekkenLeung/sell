package com.jekken.controller;

import com.jekken.config.ProjectUrlConfig;
import com.jekken.enums.ResultEnum;
import com.jekken.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Create by Jekken
 * 2020/3/29 19:10
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxMpService wxOpenService;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl")String returnUrl) throws UnsupportedEncodingException {
        //1.配置
        //2.调用方法
        String url = projectUrlConfig.getWechatMpAuthorize()+"/sell/wechat/userInfo";

        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_BASE, URLEncoder.encode(returnUrl, "utf-8"));
        return "redirect:"+redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code")String code,
                           @RequestParam("state")String returnUrl){
        return getUserInfo(code,returnUrl,wxMpService);
        /*WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken=wxMpService.oauth2getAccessToken(code);
        }catch (WxErrorException e){
            log.error("【微信网页授权】{}",e);
            throw new SellException(ResultEnum.WRCHAT_MP_ERROR.getCode(),e.getError().getErrorMsg());
        }

        String openId = wxMpOAuth2AccessToken.getOpenId();
        return "redirect:"+returnUrl+"?openid="+openId;*/

    }

   @GetMapping("/qrAuthorize")
    public String qrAuthorize(@RequestParam("returnUrl")String returnUrl) throws UnsupportedEncodingException {
        String url = projectUrlConfig.getWechatOpenAuthorize()+"/sell/wechat/qrUserInfo";
        String redirectUrl = wxOpenService.buildQrConnectUrl(url,WxConsts.QrConnectScope.SNSAPI_LOGIN,URLEncoder.encode(returnUrl,"utf-8"));
        return "redirect:"+redirectUrl;
   }

   @GetMapping("/qrUserInfo")
    public String qrUserInfo(@RequestParam("code")String code,
                             @RequestParam("state")String returnUrl){
       return getUserInfo(code,returnUrl,wxOpenService);

   }

   private String getUserInfo(String code,String returnUrl,WxMpService wxOpenService){
       WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();

       try {
           wxMpOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);
       }catch (WxErrorException e){
           log.error("【微信网页授权】{}",e);
           throw new SellException(ResultEnum.WRCHAT_MP_ERROR.getCode(),e.getError().getErrorMsg());
       }
       log.info("wxMpOAuth2AccessToken = {}",wxMpOAuth2AccessToken);
       String openId = wxMpOAuth2AccessToken.getOpenId();
       return "redirect:"+returnUrl+"?openid="+openId;
   }

}
