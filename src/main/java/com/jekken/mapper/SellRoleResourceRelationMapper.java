package com.jekken.mapper;

import com.jekken.pojo.SellRoleResourceRelation;
import com.jekken.pojo.SellRoleResourceRelationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SellRoleResourceRelationMapper {
    int countByExample(SellRoleResourceRelationExample example);

    int deleteByExample(SellRoleResourceRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SellRoleResourceRelation record);

    int insertSelective(SellRoleResourceRelation record);

    List<SellRoleResourceRelation> selectByExample(SellRoleResourceRelationExample example);

    SellRoleResourceRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SellRoleResourceRelation record, @Param("example") SellRoleResourceRelationExample example);

    int updateByExample(@Param("record") SellRoleResourceRelation record, @Param("example") SellRoleResourceRelationExample example);

    int updateByPrimaryKeySelective(SellRoleResourceRelation record);

    int updateByPrimaryKey(SellRoleResourceRelation record);
}