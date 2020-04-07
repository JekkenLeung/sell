package com.jekken.service;

import com.jekken.dto.AdminParam;
import com.jekken.dto.UpdateAdminPasswordParam;
import com.jekken.pojo.SellAdmin;
import com.jekken.pojo.SellResource;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * 后台管理Service
 * Create by Jekken
 * 2020/4/1 22:46
 */
public interface SellAdminService {

    /**
     * 根据用户名获取后台管理员
     */
    SellAdmin getAdminByUsername(String username);

    /**
     * 注册
     */
    SellAdmin register(AdminParam adminParam);

    /**
     * 登录,返回token
     */
    String login(String username,String password);


    /**
     * 根据用户名或昵称分页查询用户
     */
    List<SellAdmin> list(String keyword,Integer pageSize,Integer pageNum);

    /**
     * 修改用户信息
     */
    int update(Long id,SellAdmin admin);

    /**
     * 修改密码
     */
    int updatePassword(UpdateAdminPasswordParam updateAdminPasswordParam);

    /**
     * 获取用户信息
     * @param username
     * @return
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 获取指定用用户可访问的资源
     */
    List<SellResource> getResourceList(long adminId);


}
