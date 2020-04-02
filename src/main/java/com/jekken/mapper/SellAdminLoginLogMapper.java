package com.jekken.mapper;


import com.jekken.pojo.SellAdminLoginLog;
import com.jekken.pojo.SellAdminLoginLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SellAdminLoginLogMapper {
    int countByExample(SellAdminLoginLogExample example);

    int deleteByExample(SellAdminLoginLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SellAdminLoginLog record);

    int insertSelective(SellAdminLoginLog record);

    List<SellAdminLoginLog> selectByExample(SellAdminLoginLogExample example);

    SellAdminLoginLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SellAdminLoginLog record, @Param("example") SellAdminLoginLogExample example);

    int updateByExample(@Param("record") SellAdminLoginLog record, @Param("example") SellAdminLoginLogExample example);

    int updateByPrimaryKeySelective(SellAdminLoginLog record);

    int updateByPrimaryKey(SellAdminLoginLog record);
}