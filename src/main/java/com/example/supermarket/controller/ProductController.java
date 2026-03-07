package com.example.supermarket.controller;

import com.example.supermarket.entity.Result;
import com.example.supermarket.service.ProductService;
import io.swagger.v3.oas.annotations.OpenAPI31;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(name="商品接口",description="商品增删改查相关接口")
@RestController
@RequestMapping("/product")

public class ProductController {
    @Autowired
    private ProductService productService;

    @Operation(summary="查询商品",description="查询所有商品")
    @RequestMapping("/getProduct")
    public Result getProduct() {

        return Result.success();
    }

    @Operation(summary="添加商品",description="添加商品")
    @RequestMapping("/addProduct")
    public Result addProduct(){

        return Result.success();
    }
}
