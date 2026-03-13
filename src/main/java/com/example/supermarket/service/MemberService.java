package com.example.supermarket.service;

import com.example.supermarket.common.DTO.MemberDto;
import com.example.supermarket.common.DTO.PageDto;
import com.example.supermarket.common.VO.MemberVo;
import com.example.supermarket.common.entity.PageResult;

import java.util.List;

/**
 * 实现功能【】
 * <p>
 *
 * </p>
 *
 * @author chen.yang
 * @date 2026-03-13 09:47:56
 */
public interface MemberService {
    List<MemberVo> findAllMember();

    MemberVo findMemberById(Long id);

    void addMember(MemberVo member);

    void updateMember(MemberVo member);

    void deleteMember(Long id);

    PageResult<MemberVo> findMemberByPage(Integer pageNum, Integer pageSize);


    List<MemberVo> queryMember(MemberDto memberDto);

    void updateMembers(List<MemberVo> memberList);

    void deleteMembers(List<Long> idList);
}
