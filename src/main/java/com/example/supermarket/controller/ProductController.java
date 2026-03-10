package com.example.supermarket.controller;

import com.example.supermarket.common.DTO.ProductDto;
import com.example.supermarket.common.DTO.ProductQueryDto;
import com.example.supermarket.common.VO.ProductVo;
import com.example.supermarket.common.entity.ProductEntity;
import com.example.supermarket.common.entity.Result;
import com.example.supermarket.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.rmi.server.LogStream.log;
@Slf4j
@RestController
@RequestMapping("/product")
@Tag(name="商品接口",description="商品增删改查相关接口")


public class ProductController {
    @Autowired
    private ProductService productService;

    @Operation(summary="查询商品",description="查询所有商品")
    @GetMapping("/getProduct")
    public Result findAllProduct() {
        log.info("查询所有商品");
        List<ProductVo> productList=productService.findAllProduct();
        log.info("查询所有商品成功");
        return Result.success(productList);
    }

    @Operation(summary="查询商品",description="根据id查询商品")
    @GetMapping("/getProduct/{id}")
    public Result findProductById(@PathVariable Long id) {
        try {
            log.info("根据id查询商品");
            ProductVo product=productService.findProductById(id);
            if(product!=null){
                log.info("根据id查询商品成功");
                return Result.success(product);
            }else{
                log.info("根据id查询商品失败");
                return Result.error("商品不存在");
            }
        } catch (Exception e) {
            log.error("根据id查询商品失败"+e.getMessage());
            return Result.error("根据id查询商品失败"+e.getMessage());
        }
    }

    @Operation(summary="添加商品",description="添加商品")
    @PostMapping("/addProduct")
    public Result addProduct(@RequestBody ProductDto product){
        try {
            log.info("添加商品");
            productService.addProduct(product);
            log.info("添加商品成功");
            return Result.success();
        } catch (Exception e) {
            return Result.error("添加商品失败"+e.getMessage());
        }
    }
    @Operation(summary="删除商品",description="删除商品")
    @DeleteMapping("/deleteProduct/{id}")
    public Result deleteProduct(@PathVariable Long id){
        try {
            log.info("删除商品");
            productService.deleteProduct(id);
            log.info("删除商品成功");
            return Result.success();
        } catch (Exception e) {
            return Result.error("删除商品失败"+e.getMessage());
        }
    }
    @Operation(summary="修改商品",description="修改商品信息")
    @PutMapping("/updateProduct")
    public Result updateProduct(@RequestBody ProductDto product){
        try {
            log.info("修改商品");
            productService.UpdateProduct(product);
            log.info("修改商品成功");
            return Result.success();
        } catch (Exception e) {
            return Result.error("修改商品失败"+e.getMessage());
        }
    }

    @Operation(summary = "动态查询",description = "根据不同条件查询商品")
    @PostMapping("/queryProduct")
    public Result queryProduct(@RequestBody ProductQueryDto productQueryDto){
        try {
            log.info("动态查询商品");
            List<ProductVo> productList=productService.queryProduct(productQueryDto);
            log.info("动态查询商品成功");
            return Result.success(productList);
        } catch (Exception e) {
            return Result.error("多条件查询商品失败"+e.getMessage());
        }
    }

    @Operation(summary = "多态修改",description = "多态修改商品信息")
    @PutMapping("/updateProducts")
    public Result updateProducts(@RequestBody List<ProductDto> productList){
        try{
            log.info("多态修改商品信息");
            productService.updateProducts(productList);
            log.info("多态修改商品信息成功");
            return Result.success();
        }catch(Exception e){
        return Result.error("多态修改商品信息失败"+e.getMessage());
        }
    }

    @Operation(summary = "批量删除",description = "批量删除商品")
    @DeleteMapping("/deleteProducts")
    public Result deleteProducts(@RequestBody List<Long> idList){
        try{
                log.info("批量删除商品");
                productService.deleteProducts(idList);
                return Result.success();
        }catch (Exception e){
            return Result.error("批量删除商品失败"+e.getMessage());
        }
    }
}
