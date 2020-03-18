package com.jekken.service.impl;

import com.jekken.config.UpYunConfig;
import com.jekken.dao.ProductInfoDao;
import com.jekken.dto.CartDTO;
import com.jekken.enums.ProductStatusEnum;
import com.jekken.enums.ResultEnum;
import com.jekken.exception.SellException;
import com.jekken.pojo.ProductInfo;
import com.jekken.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Create by Jekken
 * 2020/3/17 15:25
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Autowired
    private UpYunConfig upYunConfig;

    @Override
    public ProductInfo findOne(String productId) {
        Optional<ProductInfo> productInfo = productInfoDao.findById(productId);

        productInfo.ifPresent(e->e.addImageHost(upYunConfig.getImageHost()));

        return productInfo.orElse(null);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findByProductStatus(ProductStatusEnum.UP.getCode()).stream().map(e->e.addImageHost(upYunConfig.getImageHost())).collect(Collectors.toList());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        Page<ProductInfo> productInfoPage = productInfoDao.findAll(pageable);
        productInfoPage.getContent().stream().forEach(e->e.addImageHost(upYunConfig.getImageHost()));
        return productInfoPage;
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoDao.save(productInfo).addImageHost(upYunConfig.getImageHost());
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo = productInfoDao.findById(cartDTO.getProductId()).orElse(null);
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoDao.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo = productInfoDao.findById(cartDTO.getProductId()).orElse(null);
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result<0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
        productInfoDao.save(productInfo);
    }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = productInfoDao.findById(productId).orElse(null);
        if (productInfo == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());

        return productInfoDao.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = productInfoDao.findById(productId).orElse(null);
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return productInfoDao.save(productInfo);
    }
}
