package com.jekken.service;

import com.jekken.pojo.ProductCategory;

import java.util.List;

/**
 * 类目Service
 * Create by Jekken
 * 2020/3/23 10:18
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);

}
