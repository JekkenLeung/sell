package com.jekken.mapper;


import com.jekken.pojo.SellAdminPermissionRelation;
import com.jekken.pojo.SellAdminPermissionRelationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SellAdminPermissionRelationMapper {
    int countByExample(SellAdminPermissionRelationExample example);

    int deleteByExample(SellAdminPermissionRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SellAdminPermissionRelation record);

    int insertSelective(SellAdminPermissionRelation record);

    List<SellAdminPermissionRelation> selectByExample(SellAdminPermissionRelationExample example);

    SellAdminPermissionRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SellAdminPermissionRelation record, @Param("example") SellAdminPermissionRelationExample example);

    int updateByExample(@Param("record") SellAdminPermissionRelation record, @Param("example") SellAdminPermissionRelationExample example);

    int updateByPrimaryKeySelective(SellAdminPermissionRelation record);

    int updateByPrimaryKey(SellAdminPermissionRelation record);
}