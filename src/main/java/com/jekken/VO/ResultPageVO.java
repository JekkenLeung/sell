package com.jekken.VO;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * Create by Jekken
 * 2020/4/11 20:27
 */
@Data
public class ResultPageVO<T>{
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Long total;
    private List<T> list;

    /**
     * 将分页后的list转为分页信息
     */
    public static <T> ResultPageVO<T> restPage(List<T> list){
        ResultPageVO<T> result =new ResultPageVO<>();
        PageInfo<T> pageInfo = new PageInfo<>(list);
        result.setTotalPage(pageInfo.getPages());
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotal(pageInfo.getTotal());
        result.setList(pageInfo.getList());
        return result;

    }



}
