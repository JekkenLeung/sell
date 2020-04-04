package com.jekken.mapper;


import com.jekken.pojo.SellAdminRoleRelation;
import com.jekken.pojo.SellAdminRoleRelationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SellAdminRoleRelationMapper {
    int countByExample(SellAdminRoleRelationExample example);

    int deleteByExample(SellAdminRoleRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SellAdminRoleRelation record);

    int insertSelective(SellAdminRoleRelation record);

    List<SellAdminRoleRelation> selectByExample(SellAdminRoleRelationExample example);

    SellAdminRoleRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SellAdminRoleRelation record, @Param("example") SellAdminRoleRelationExample example);

    int updateByExample(@Param("record") SellAdminRoleRelation record, @Param("example") SellAdminRoleRelationExample example);

    int updateByPrimaryKeySelective(SellAdminRoleRelation record);

    int updateByPrimaryKey(SellAdminRoleRelation record);
}