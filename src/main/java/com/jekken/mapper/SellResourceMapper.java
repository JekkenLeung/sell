package com.jekken.mapper;


import com.jekken.pojo.SellResource;
import com.jekken.pojo.SellResourceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SellResourceMapper {
    int countByExample(SellResourceExample example);

    int deleteByExample(SellResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SellResource record);

    int insertSelective(SellResource record);

    List<SellResource> selectByExample(SellResourceExample example);

    SellResource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SellResource record, @Param("example") SellResourceExample example);

    int updateByExample(@Param("record") SellResource record, @Param("example") SellResourceExample example);

    int updateByPrimaryKeySelective(SellResource record);

    int updateByPrimaryKey(SellResource record);
}