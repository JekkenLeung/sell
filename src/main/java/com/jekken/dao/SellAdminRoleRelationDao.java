package com.jekken.dao;

import com.jekken.pojo.SellResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台用户与角色管理
 * Create by Jekken
 * 2020/4/5 17:39
 */
public interface SellAdminRoleRelationDao {

    /**
     * 获取用户所有可访问资源
     */
    List<SellResource> getResourceList(@Param("adminId")Long adminId);

}
