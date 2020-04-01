package com.jekken.mapper;


import com.jekken.pojo.SellAdmin;
import com.jekken.pojo.SellAdminExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SellAdminMapper {
    int countByExample(SellAdminExample example);

    int deleteByExample(SellAdminExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SellAdmin record);

    int insertSelective(SellAdmin record);

    List<SellAdmin> selectByExample(SellAdminExample example);

    SellAdmin selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SellAdmin record, @Param("example") SellAdminExample example);

    int updateByExample(@Param("record") SellAdmin record, @Param("example") SellAdminExample example);

    int updateByPrimaryKeySelective(SellAdmin record);

    int updateByPrimaryKey(SellAdmin record);
}