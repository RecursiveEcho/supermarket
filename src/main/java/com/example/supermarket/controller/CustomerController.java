package com.example.supermarket.controller;


import com.example.supermarket.pojo.DTO.CustomerDto;
import com.example.supermarket.pojo.VO.CustomerVo;
import com.example.supermarket.pojo.entity.PageResult;
import com.example.supermarket.pojo.entity.Result;
import com.example.supermarket.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 实现功能【】
 * <p>
 *
 * </p>
 *
 * @author chen.yang
 * @date 2026-03-15 10:22:08
 */
@Slf4j
@RestController
@RequestMapping("/customer")
@Tag(name="客户接口",description = "客户增删改查相关接口")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Operation(summary="查询客户",description="查询所有客户")
    @GetMapping("/getCustomer")
    public Result findAllCustomer() {
        List<CustomerVo> customerList= customerService.findAllCustomer();
        return Result.success(customerList);
    }

    @Operation(summary="查询客户",description="根据 id 查询客户")
    @GetMapping("/getCustomer/{id}")
    public Result findCustomerById(@PathVariable Long id) {
        CustomerVo customer=customerService.findCustomerById(id);
        return Result.success(customer);
    }

    @Operation(summary="添加客户",description="添加客户")
    @PostMapping("/addCustomer")
    public Result addCustomer(@RequestBody CustomerVo customer){
        customerService.addCustomer(customer);
        return Result.success();
    }

    @Operation(summary="修改客户",description="修改客户")
    @PutMapping("/updateCustomer")
    public Result updateCustomer(@RequestBody CustomerVo customer){
        customerService.updateCustomer(customer);
        return Result.success();
    }

    @Operation(summary="删除客户",description="删除客户")
    @DeleteMapping("/deleteCustomer/{id}")
    public Result deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return Result.success();
    }

    @Operation(summary="查询客户",description="分页查询客户")
    @GetMapping("/getCustomerByPage")
    public Result findCustomerByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "10") Integer pageSize){
        PageResult<CustomerVo> pageResult= customerService.findCustomerByPage(pageNum, pageSize);
        return Result.success(pageResult);
    }

    @Operation(summary="动态查询客户",description="动态查询客户")
    @PostMapping("/queryCustomer")
    public Result queryCustomer(@RequestBody CustomerDto customerDto){
        List<CustomerVo> customerList = customerService.queryCustomer(customerDto);
        return Result.success(customerList);
    }

    @Operation(summary="多态修改客户信息",description="多态修改客户信息")
    @PutMapping("/updateCustomers")
    public Result updateCustomers(@RequestBody List<CustomerVo> customerList){
        customerService.updateCustomers(customerList);
        return Result.success();
    }

    @Operation(summary="批量删除客户",description="批量删除客户")
    @DeleteMapping("/deleteCustomers")
    public Result deleteCustomers(@RequestBody List<Long> idList){
        customerService.deleteCustomers(idList);
        return Result.success();
    }

}
