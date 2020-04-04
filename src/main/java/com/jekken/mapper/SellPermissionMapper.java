package com.jekken.mapper;

import com.jekken.pojo.SellPermission;
import com.jekken.pojo.SellPermissionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SellPermissionMapper {
    int countByExample(SellPermissionExample example);

    int deleteByExample(SellPermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SellPermission record);

    int insertSelective(SellPermission record);

    List<SellPermission> selectByExample(SellPermissionExample example);

    SellPermission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SellPermission record, @Param("example") SellPermissionExample example);

    int updateByExample(@Param("record") SellPermission record, @Param("example") SellPermissionExample example);

    int updateByPrimaryKeySelective(SellPermission record);

    int updateByPrimaryKey(SellPermission record);
}