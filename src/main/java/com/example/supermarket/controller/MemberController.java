package com.example.supermarket.controller;

import com.example.supermarket.pojo.DTO.MemberDto;
import com.example.supermarket.pojo.VO.MemberVo;
import com.example.supermarket.pojo.entity.PageResult;
import com.example.supermarket.pojo.entity.Result;
import com.example.supermarket.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Operation(summary="查询会员",description="查询所有会员")
    @GetMapping("/getMember")
    public Result findAllMember() {
        List<MemberVo> memberList= memberService.findAllMember();
        return Result.success(memberList);
    }

    @Operation(summary="查询会员",description="根据 id 查询会员")
    @GetMapping("/getMember/{id}")
    public Result findMemberById(@PathVariable Long id) {
        MemberVo member=memberService.findMemberById(id);
        return Result.success(member);
    }

    @Operation(summary="添加会员",description="添加会员")
    @PostMapping("/addMember")
    public Result addMember(@RequestBody MemberVo member){
        memberService.addMember(member);
        return Result.success();
    }

    @Operation(summary="修改会员",description="修改会员")
    @PutMapping("/updateMember")
    public Result updateMember(@RequestBody MemberVo member){
        memberService.updateMember(member);
        return Result.success();
    }

    @Operation(summary="删除会员",description="删除会员")
    @DeleteMapping("/deleteMember/{id}")
    public Result deleteMember(@PathVariable Long id){
        memberService.deleteMember(id);
        return Result.success();
    }

    @Operation(summary="查询会员",description="分页查询会员")
    @GetMapping("/getMemberByPage")
    public Result findMemberByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "10") Integer pageSize){
        PageResult<MemberVo> pageResult= memberService.findMemberByPage(pageNum, pageSize);
        return Result.success(pageResult);
    }

    @Operation(summary="动态查询会员",description="动态查询会员")
    @PostMapping("/queryMember")
    public Result queryMember(@RequestBody MemberDto MemberDto){
        List<MemberVo> memberList = memberService.queryMember(MemberDto);
        return Result.success(memberList);
    }

    @Operation(summary="多态修改会员信息",description="多态修改会员信息")
    @PutMapping("/updateMembers")
    public Result updateMembers(@RequestBody List<MemberVo> memberList){
        memberService.updateMembers(memberList);
        return Result.success();
    }

    @Operation(summary="批量删除会员",description="批量删除会员")
    @DeleteMapping("/deleteMembers")
    public Result deleteMembers(@RequestBody List<Long> idList){
        memberService.deleteMembers(idList);
        return Result.success();
    }

}