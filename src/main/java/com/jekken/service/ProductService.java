package com.jekken.service;

import com.jekken.dto.CartDTO;
import com.jekken.pojo.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Create by Jekken
 * 2020/3/17 15:01
 */
public interface ProductService {

    ProductInfo findOne(String productId);

    /**
     * 查询所有在家商品
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    /**
     * 加库存
     */
    void increaseStock(List<CartDTO> cartDTOList);

    /**
     * 减库存
     */
    void decreaseStock(List<CartDTO> cartDTOList);

    /**
     * 上架
     */
    ProductInfo onSale(String productId);

    /**
     * 下架
     */
    ProductInfo offSale(String productId);
}
