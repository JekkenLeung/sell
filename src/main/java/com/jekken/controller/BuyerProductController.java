package com.jekken.controller;

import com.jekken.VO.ProductInfoVO;
import com.jekken.VO.ProductVO;
import com.jekken.VO.ResultVO;
import com.jekken.pojo.ProductCategory;
import com.jekken.pojo.ProductInfo;
import com.jekken.service.CategoryService;
import com.jekken.service.ProductService;
import com.jekken.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by Jekken
 * 2020/3/29 11:44
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list(@RequestParam(value = "sellerId",required = false)String sellerId){
        //1.查询所有商家商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //2.查询类目
        List<Integer> categoryTypeList = productInfoList.stream().map(e->e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //3.数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory:productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo:productInfoList){
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);

    }


}
