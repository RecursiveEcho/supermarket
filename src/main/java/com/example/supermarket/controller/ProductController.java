package com.example.supermarket.controller;

import com.example.supermarket.entity.Product_entity;
import com.example.supermarket.entity.Result;
import com.example.supermarket.service.ProductService;
import io.swagger.v3.oas.annotations.OpenAPI31;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.rmi.server.LogStream.log;

@Tag(name="商品接口",description="商品增删改查相关接口")
@RestController
@RequestMapping("/product")
@Slf4j

public class ProductController {
    @Autowired
    private ProductService productService;

    @Operation(summary="查询商品",description="查询所有商品")
    @GetMapping("/getProduct")
    public Result findAllProduct() {
        log("查询所有商品");
        List<Product_entity> productList=productService.findAllProduct();
        log("查询所有商品成功");
        return Result.success(productList);
    }

    @Operation(summary="查询商品",description="根据id查询商品")
    @GetMapping("/getProduct/{id}")
    public Result findProductById(@PathVariable Long id) {
        log("根据id查询商品");
        Product_entity product=productService.findProductById(id);
        if(product!=null){
            log("根据id查询商品成功");
            return Result.success(product);
        }else{
            log("根据id查询商品失败");
            return Result.error("商品不存在");
        }
    }

    @Operation(summary="添加商品",description="添加商品")
    @PostMapping("/addProduct")
    public Result addProduct(@RequestBody Product_entity product_entity){
        log("添加商品");
        productService.addProduct(product_entity);
        log("添加商品成功");
        return Result.success();
    }
    @Operation(summary="删除商品",description="删除商品")
    @DeleteMapping("/deleteProduct/{id}")
    public Result deleteProduct(@PathVariable Long id){
        log("删除商品");
        productService.deleteProduct(id);
        log("删除商品成功");
        return Result.success();
    }
    @Operation(summary="修改商品",description="修改商品信息")
    @PutMapping("/updateProduct")
    public Result updateProduct(@RequestBody Product_entity product_entity){
        log("修改商品");
        productService.UpdateProduct(product_entity);
        log("修改商品成功");
        return Result.success();
    }
}
