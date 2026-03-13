package com.example.supermarket.controller;

import com.example.supermarket.common.VO.ClerkVo;
import com.example.supermarket.common.entity.PageResult;
import com.example.supermarket.common.entity.Result;
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

    /**
     * 查询所有员工
     * @return
     */
    @Operation(summary="查询员工",description="查询所有员工")
    @GetMapping("/getAllClerk")
    public Result findAllClerk() {
        try {
            log.info("查询所有员工");
            List<ClerkVo> clerkList= clerkService.findAllClerk();
            log.info("查询所有员工成功");
            return Result.success(clerkList);
        } catch (Exception e) {
            return Result.error("查询所有员工失败:{}"+e.getMessage());
        }
    }

    /**
     * 根据 id 查询员工
     * @param id
     * @return
     */
    @Operation(summary="查询员工",description="根据 id 查询员工")
    @GetMapping("/getByIdClerk/{id}")
    public Result findClerkById(@PathVariable Long id) {
        try {
            log.info("根据 id 查询员工");
            ClerkVo clerk=clerkService.findClerkById(id);
            if(clerk!=null){
                log.info("根据 id 查询员工成功");
                return Result.success(clerk);
            }else{
                log.info("根据 id 查询员工失败");
                return Result.error("员工不存在");
            }
        } catch (Exception e) {
            return Result.error("根据 id 查询员工失败"+e.getMessage());
        }
    }

    /**
     * 添加员工
     * @param clerk
     * @return
     */
    @Operation(summary="添加员工",description="添加员工")
    @PostMapping("/addClerk")
    public Result addClerk(@RequestBody ClerkVo clerk){
        try {
            log.info("添加员工");
            clerkService.addClerk(clerk);
            log.info("添加员工成功");
            return Result.success();
        }catch (Exception e){
            return Result.error("添加员工失败"+e.getMessage());
        }
    }

    /**
     * 修改员工信息
     * @param clerk
     * @return
     */
    @Operation(summary="修改员工信息",description="修改员工信息")
    @PostMapping("/updateClerk")
    public Result updateClerk(@RequestBody ClerkVo clerk){
        try {
            log.info("修改员工信息");
            clerkService.updateClerk(clerk);
            log.info("修改员工信息成功");
            return Result.success();
        }catch (Exception e){
            return Result.error("修改员工信息失败"+e.getMessage());
        }
    }

    /**
     * 删除员工
     * @param id
     * @return
     */
    @Operation(summary="删除员工",description="删除员工")
    @DeleteMapping("/deleteClerk/{id}")
    public Result deleteClerk(@PathVariable Long id){
        try {
            log.info("删除员工");
            clerkService.deleteClerk(id);
            log.info("删除员工成功");
            return Result.success();
        }catch (Exception e){
            return Result.error("删除员工失败"+e.getMessage());
        }
    }

    /**
     * 分页查询员工
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Operation(summary="分页查询员工",description="分页查询员工")
    @GetMapping("/getByPage")
    public Result findClerkByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "10") Integer pageSize){
        try {
            log.info("分页查询员工");
            PageResult<ClerkVo> pageResult= clerkService.findClerkByPage(pageNum, pageSize);
            log.info("分页查询员工成功");
            return Result.success(pageResult);
        } catch (Exception e) {
            return Result.error("分页查询员工失败：" + e.getMessage());
        }
    }

    /**
     * 动态查询员工
     * @param clerkDto
     * @return
     */
    @Operation(summary="动态查询员工",description="动态查询员工")
    @PostMapping("/queryClerk")
    public Result queryClerk(@RequestBody ClerkVo clerkDto){
        try {
            log.info("动态查询员工");
            List<ClerkVo> clerkList = clerkService.queryClerk(clerkDto);
            log.info("动态查询员工成功");
            return Result.success(clerkList);
        }catch (Exception e){
            log.error("动态查询员工失败", e);
            return Result.error("动态查询员工失败：" + e.getMessage());
        }
    }
    
    /**
     * 批量修改员工信息
     * @param clerkList
     * @return
     */
    @Operation(summary="批量修改员工信息",description="批量修改员工信息")
    @PostMapping("/updateClerks")
    public Result updateClerks(@RequestBody List<ClerkVo> clerkList){
        try {
            log.info("批量修改员工信息");
            clerkService.updateClerks(clerkList);
            log.info("批量修改员工信息成功");
            return Result.success();
        } catch (Exception e) {
            return Result.error("批量修改员工信息失败：" + e.getMessage());
        }
    }
    
    /**
     * 批量删除员工
     * @param idList
     * @return
     */
    @Operation(summary="批量删除员工",description="批量删除员工")
    @PostMapping("/deleteClerks")
    public Result deleteClerks(@RequestBody List<Long> idList){
        try {
            log.info("批量删除员工");
            clerkService.deleteClerks(idList);
            log.info("批量删除员工成功");
            return Result.success();
        } catch (Exception e) {
            return Result.error("批量删除员工失败：" + e.getMessage());
        }
    }
}