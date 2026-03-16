package com.example.supermarket.controller;

import com.example.supermarket.pojo.VO.ClerkVo;
import com.example.supermarket.pojo.entity.PageResult;
import com.example.supermarket.pojo.entity.Result;
import com.example.supermarket.service.ClerkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *员工
 * @author chen.yang
 * @date 2026-03-13 19:02:11
 */
@Slf4j
@RestController
@RequestMapping("/clerk")
@Tag(name="员工接口",description = "员工增删改查相关接口")
public class ClerkController {
    @Autowired
    private ClerkService clerkService;

    @Operation(summary="查询员工",description="查询所有员工")
    @GetMapping("/getAllClerk")
    public Result findAllClerk() {
        List<ClerkVo> clerkList= clerkService.findAllClerk();
        return Result.success(clerkList);
    }

    @Operation(summary="查询员工",description="根据 id 查询员工")
    @GetMapping("/getByIdClerk/{id}")
    public Result findClerkById(@PathVariable Long id) {
        ClerkVo clerk=clerkService.findClerkById(id);
        return Result.success(clerk);
    }

    @Operation(summary="添加员工",description="添加员工")
    @PostMapping("/addClerk")
    public Result addClerk(@RequestBody ClerkVo clerk){
        clerkService.addClerk(clerk);
        return Result.success();
    }

    @Operation(summary="修改员工信息",description="修改员工信息")
    @PostMapping("/updateClerk")
    public Result updateClerk(@RequestBody ClerkVo clerk){
        clerkService.updateClerk(clerk);
        return Result.success();
    }

    @Operation(summary="删除员工",description="删除员工")
    @DeleteMapping("/deleteClerk/{id}")
    public Result deleteClerk(@PathVariable Long id){
        clerkService.deleteClerk(id);
        return Result.success();
    }

    @Operation(summary="分页查询员工",description="分页查询员工")
    @GetMapping("/getByPage")
    public Result findClerkByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "10") Integer pageSize){
        PageResult<ClerkVo> pageResult= clerkService.findClerkByPage(pageNum, pageSize);
        return Result.success(pageResult);
    }

    @Operation(summary="动态查询员工",description="动态查询员工")
    @PostMapping("/queryClerk")
    public Result queryClerk(@RequestBody ClerkVo clerkDto){
        List<ClerkVo> clerkList = clerkService.queryClerk(clerkDto);
        return Result.success(clerkList);
    }
    
    @Operation(summary="批量修改员工信息",description="批量修改员工信息")
    @PostMapping("/updateClerks")
    public Result updateClerks(@RequestBody List<ClerkVo> clerkList){
        clerkService.updateClerks(clerkList);
        return Result.success();
    }
    
    @Operation(summary="批量删除员工",description="批量删除员工")
    @PostMapping("/deleteClerks")
    public Result deleteClerks(@RequestBody List<Long> idList){
        clerkService.deleteClerks(idList);
        return Result.success();
    }
}