package com.jekken.component;

import cn.hutool.json.JSONUtil;
import com.jekken.enums.ResultEnum;
import com.jekken.utils.ResultVOUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 匿名用户(未登录或登录过期)返回结果
 * Create by Jekken
 * 2020/4/6 18:22
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(ResultVOUtil.error(ResultEnum.UN_AUTHORIZED.getCode(),ResultEnum.UN_AUTHORIZED.getMessage())));
        response.getWriter().flush();
    }
}
