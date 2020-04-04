package com.jekken.mapper;


import com.jekken.pojo.SellRole;
import com.jekken.pojo.SellRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SellRoleMapper {
    int countByExample(SellRoleExample example);

    int deleteByExample(SellRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SellRole record);

    int insertSelective(SellRole record);

    List<SellRole> selectByExample(SellRoleExample example);

    SellRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SellRole record, @Param("example") SellRoleExample example);

    int updateByExample(@Param("record") SellRole record, @Param("example") SellRoleExample example);

    int updateByPrimaryKeySelective(SellRole record);

    int updateByPrimaryKey(SellRole record);
}