package com.example.supermarket.controller;

import com.example.supermarket.entity.Product_entity;
import com.example.supermarket.entity.Result;
import com.example.supermarket.service.ProductService;
import io.swagger.v3.oas.annotations.OpenAPI31;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name="商品接口",description="商品增删改查相关接口")
@RestController
@RequestMapping("/product")

public class ProductController {
    @Autowired
    private ProductService productService;

    @Operation(summary="查询商品",description="查询所有商品")
    @RequestMapping("/getProduct")
    public Result findAllProduct() {
        List<Product_entity> productList=productService.findAllProduct();
        return Result.success(productList);
    }

    @Operation(summary="添加商品",description="添加商品")
    @RequestMapping("/addProduct")
    public Result addProduct(@RequestBody Product_entity product_entity){
        productService.addProduct(product_entity);
        return Result.success();
    }

    @Operation(summary="删除商品",description="删除商品")
    @RequestMapping("/deleteProduct")
    public Result deleteProduct(Long id){
        productService.deleteProduct(id);
        return Result.success();
    }
}
