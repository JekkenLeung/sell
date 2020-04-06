package com.jekken.component;

import cn.hutool.json.JSONUtil;
import com.jekken.enums.ResultEnum;
import com.jekken.utils.ResultVOUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * spring security 没有权限访问时自定义返回结果
 * Create by Jekken
 * 2020/4/6 18:08
 */
public class RestfulAccessDenieHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {

        httpServletResponse.setHeader("Access-Control-Allow-Origin","*");
        httpServletResponse.setHeader("Cache-Control","no-cache");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().println(JSONUtil.parse(ResultVOUtil.error(ResultEnum.PERMISSION_DENIED.getCode(),ResultEnum.PERMISSION_DENIED.getMessage())));
        httpServletResponse.getWriter().flush();
    }
}
