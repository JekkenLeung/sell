package com.jekken.mapper;


import com.jekken.pojo.SellRolePermissionRelation;
import com.jekken.pojo.SellRolePermissionRelationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SellRolePermissionRelationMapper {
    int countByExample(SellRolePermissionRelationExample example);

    int deleteByExample(SellRolePermissionRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SellRolePermissionRelation record);

    int insertSelective(SellRolePermissionRelation record);

    List<SellRolePermissionRelation> selectByExample(SellRolePermissionRelationExample example);

    SellRolePermissionRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SellRolePermissionRelation record, @Param("example") SellRolePermissionRelationExample example);

    int updateByExample(@Param("record") SellRolePermissionRelation record, @Param("example") SellRolePermissionRelationExample example);

    int updateByPrimaryKeySelective(SellRolePermissionRelation record);

    int updateByPrimaryKey(SellRolePermissionRelation record);
}