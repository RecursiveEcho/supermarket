package com.example.supermarket.controller;

import com.example.supermarket.pojo.DTO.ProductDto;
import com.example.supermarket.pojo.DTO.ProductQueryDto;
import com.example.supermarket.pojo.VO.ProductVo;
import com.example.supermarket.pojo.entity.PageResult;
import com.example.supermarket.pojo.entity.Result;
import com.example.supermarket.mapper.ProductMapper;
import com.example.supermarket.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
@Tag(name="商品接口",description="商品增删改查相关接口")


public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;

    @Operation(summary="查询商品",description="查询所有商品")
    @GetMapping("/getProduct")
    public Result findAllProduct() {
        List<ProductVo> productList=productService.findAllProduct();
        return Result.success(productList);
    }

    @Operation(summary="查询商品",description="根据 id 查询商品")
    @GetMapping("/getProduct/{id}")
    public Result findProductById(@PathVariable Long id) {
        ProductVo product=productService.findProductById(id);
        return Result.success(product);
    }

    @Operation(summary="添加商品",description="添加商品")
    @PostMapping("/addProduct")
    public Result addProduct(@RequestBody ProductDto product){
        productService.addProduct(product);
        return Result.success();
    }

    @Operation(summary="删除商品",description="删除商品")
    @DeleteMapping("/deleteProduct/{id}")
    public Result deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return Result.success();
    }

    @Operation(summary="修改商品",description="修改商品信息")
    @PutMapping("/updateProduct")
    public Result updateProduct(@RequestBody ProductDto product){
        productService.updateProduct(product);
        return Result.success();
    }

    @Operation(summary = "动态查询",description = "根据不同条件查询商品")
    @PostMapping("/queryProduct")
    public Result queryProduct(@RequestBody ProductQueryDto productQueryDto){
        List<ProductVo> productList=productService.queryProduct(productQueryDto);
        return Result.success(productList);
    }

    @Operation(summary = "多态修改",description = "多态修改商品信息")
    @PutMapping("/updateProducts")
    public Result updateProducts(@RequestBody List<ProductDto> productList){
        productService.updateProducts(productList);
        return Result.success();
    }

    @Operation(summary = "批量删除",description = "批量删除商品")
    @DeleteMapping("/deleteProducts")
    public Result deleteProducts(@RequestBody List<Long> idList){
        productService.deleteProducts(idList);
        return Result.success();
    }

    @Operation(summary = "分页查询",description = "分页查询商品，返回分页结果")
    @GetMapping("/pageProduct")
    public Result findMemberByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "10") Integer pageSize){
        PageResult<ProductVo> pageResult= productService.findProductByPage(pageNum, pageSize);
        return Result.success(pageResult);
    }
}
