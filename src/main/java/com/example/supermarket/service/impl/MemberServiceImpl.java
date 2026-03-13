package com.example.supermarket.service.impl;

import com.example.supermarket.common.DTO.MemberDto;
import com.example.supermarket.common.DTO.PageDto;
import com.example.supermarket.common.VO.MemberVo;
import com.example.supermarket.common.entity.PageResult;
import com.example.supermarket.mapper.MemberMapper;
import com.example.supermarket.service.MemberService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员服务实现类
 * @author chen.yang
 * @date 2026-03-13 09:47:10
 */
@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;
    /**
     * 查询所有会员
     * @return
     */
    @Operation(summary = "查询所有会员",description = "查询所有会员")
    @Override
    public List<MemberVo> findAllMember() {
         return memberMapper.findAllMember();
    }

    /**
     * 根据id查询会员
     * @param id
     * @return
     */
    @Operation(summary = "根据id查询会员",description = "根据id查询会员")
    @Override
    public MemberVo findMemberById(Long id) {
        return memberMapper.findMemberById(id);
    }

    @Operation(summary = "添加会员",description = "添加会员")
    /**
     * 添加会员
     * @param member
     */
    @Override
    public void addMember(MemberVo member) {
        memberMapper.addMember(member);
    }

    @Operation(summary = "修改会员信息",description = "修改会员信息")
    /**
     * 修改会员信息
     * @param member
     */
    @Override
    public void updateMember(MemberVo member) {
        memberMapper.updateMember(member);
    }

    @Operation(summary = "删除会员",description = "删除会员")
    /**
     * 删除会员
     * @param id
     */
    @Override
    public void deleteMember(Long id) {
        memberMapper.deleteMember(id);
    }

    @Operation(summary = "分页查询会员",description = "分页查询会员")
    /**
     * 分页查询会员
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageResult<MemberVo> findMemberByPage(Integer pageNum, Integer pageSize) {
        // 打印日志确认参数
        log.info("分页查询 - pageNum: {}, pageSize: {}", pageNum, pageSize);
        
        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        List<MemberVo> memberList = memberMapper.findAllMember();
        
        // 使用 PageInfo 包装查询结果
        PageInfo<MemberVo> pageInfo = new PageInfo<>(memberList);
        
        // 打印日志确认分页信息
        log.info("分页结果 - pageNum: {}, pageSize: {}, total: {}", 
            pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal());
        
        // 使用静态工厂方法构建分页结果
        return PageResult.of(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getList());
    }

    @Operation(summary = "查询会员",description = "查询会员")
    /**
     * 查询会员
     * @param memberDto
     * @return
     */
    @Override
    public List<MemberVo> queryMember(MemberDto memberDto) {
        return memberMapper.queryMember(memberDto);
    }

    @Operation(summary = "批量修改会员信息",description = "批量修改会员信息")
    /**
     * 修改会员信息
     * @param memberList
     */
    @Override
    public void updateMembers(List<MemberVo> memberList) {
        memberMapper.updateMembers(memberList);
    }

    @Operation(summary = "批量删除会员",description = "批量删除会员")
    /**
     * 删除会员
     * @param idList
     */
    @Override
    public void deleteMembers(List<Long> idList) {
        memberMapper.deleteMembers(idList);
    }
}