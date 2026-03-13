package com.example.supermarket.controller;

import com.example.supermarket.common.DTO.MemberDto;
import com.example.supermarket.common.DTO.PageDto;
import com.example.supermarket.common.VO.MemberVo;
import com.example.supermarket.common.entity.PageResult;
import com.example.supermarket.common.entity.Result;
import com.example.supermarket.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author chen.yang
 * @date 2026-03-13 09:44:11
 */
@Slf4j
@RestController
@RequestMapping("/member")
@Tag(name="会员接口",description = "会员增删改查相关接口")
public class MemberController {
    @Autowired
    private MemberService memberService;

    /**
     * 查询所有会员
     * @return
     */
    @Operation(summary="查询会员",description="查询所有会员")
    @GetMapping("/getMember")
    public Result findAllMember() {
        try {
            log.info("查询所有会员");
            List<MemberVo> memberList= memberService.findAllMember();
            log.info("查询所有会员成功");
            return Result.success(memberList);
        } catch (Exception e) {
            return Result.error("查询所有会员失败:{}"+e.getMessage());
        }
    }

    /**
     * 根据id查询会员
     * @param id
     * @return
     */
    @Operation(summary="查询会员",description="根据id查询会员")
    @GetMapping("/getMember/{id}")
    public Result findMemberById(@PathVariable Long id) {
        try {
            log.info("根据id查询会员");
            MemberVo member=memberService.findMemberById(id);
            if(member!=null){
                log.info("根据id查询会员成功");
                return Result.success(member);
            }else{
                log.info("根据id查询会员失败");
                return Result.error("会员不存在");
            }
        } catch (Exception e) {
            return Result.error("根据id查询会员失败"+e.getMessage());
        }
    }

    /**
     * 添加会员
     * @param member
     * @return
     */
    @Operation(summary="添加会员",description="添加会员")
    @PostMapping("/addMember")
    public Result addMember(@RequestBody MemberVo member){
        try {
            log.info("添加会员");
            memberService.addMember(member);
            log.info("添加会员成功");
            return Result.success();
        } catch (Exception e) {
            return Result.error("添加会员失败"+e.getMessage());
        }
    }

    /**
     * 修改会员
     * @param member
     * @return
     */
    @Operation(summary="修改会员",description="修改会员")
    @PutMapping("/updateMember")
    public Result updateMember(@RequestBody MemberVo member){
        try {
            log.info("修改会员");
            memberService.updateMember(member);
            log.info("修改会员成功");
            return Result.success();
        } catch (Exception e) {
            return Result.error("修改会员失败"+e.getMessage());
        }
    }

    /**
     * 删除会员
     * @param id
     * @return
     */
    @Operation(summary="删除会员",description="删除会员")
    @DeleteMapping("/deleteMember/{id}")
    public Result deleteMember(@PathVariable Long id){
        try {
            log.info("删除会员");
            memberService.deleteMember(id);
            log.info("删除会员成功");
            return Result.success();
        } catch (Exception e) {
            return Result.error("删除会员失败"+e.getMessage());
        }
    }

    /**
     * 分页查询会员
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Operation(summary="查询会员",description="分页查询会员")
    @GetMapping("/getMemberByPage")
    public Result findMemberByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "10") Integer pageSize){
        try {
            log.info("分页查询会员");
            PageResult<MemberVo> pageResult= memberService.findMemberByPage(pageNum, pageSize);
            log.info("分页查询会员成功");
            return Result.success(pageResult);
        }catch (Exception e){
            log.error("分页查询失败", e);
            return Result.error("分页查询失败：" + e.getMessage());
        }
    }

    /**
     * 动态查询会员
     * @param MemberDto
     * @return
     */
    @Operation(summary="动态查询会员",description="动态查询会员")
    @PostMapping("/queryMember")
    public Result queryMember(@RequestBody MemberDto MemberDto){
        try {
            log.info("动态查询会员");
            List<MemberVo> memberList = memberService.queryMember(MemberDto);
            log.info("动态查询会员成功");
            return Result.success(memberList);
        }catch (Exception e){
            log.error("动态查询会员失败", e);
            return Result.error("动态查询会员失败：" + e.getMessage());
        }
    }

    /**
     * 多态修改会员信息
     * @param memberList
     * @return
     */
    @Operation(summary="多态修改会员信息",description="多态修改会员信息")
    @PutMapping("/updateMembers")
    public Result updateMembers(@RequestBody List<MemberVo> memberList){
        try {
            log.info("多态修改会员信息");
            memberService.updateMembers(memberList);
            log.info("多态修改会员信息成功");
            return Result.success();
        }catch (Exception e){
            log.error("多态修改会员信息失败", e);
            return Result.error("多态修改会员信息失败：" + e.getMessage());
        }
    }

    /**
     * 批量删除会员
     * @param idList
     * @return
     */
    @Operation(summary="批量删除会员",description="批量删除会员")
    @DeleteMapping("/deleteMembers")
    public Result deleteMembers(@RequestBody List<Long> idList){
        try {
            log.info("批量删除会员");
            memberService.deleteMembers(idList);
            log.info("批量删除会员成功");
            return Result.success();
        }catch (Exception e){
            log.error("批量删除会员失败", e);
            return Result.error("批量删除会员失败：" + e.getMessage());
        }
    }

}