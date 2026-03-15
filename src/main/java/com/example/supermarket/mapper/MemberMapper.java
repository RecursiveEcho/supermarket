package com.example.supermarket.mapper;

import com.example.supermarket.pojo.DTO.MemberDto;
import com.example.supermarket.pojo.VO.MemberVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 会员类接口
 * @author chen.yang
 * @date 2026-03-13 09:48:54
 */
@Mapper
public interface MemberMapper {

    /**
     * 查询所有会员
     * @return
     */
    List<MemberVo> findAllMember();

    /**
     * 根据id查询会员
     * @param id
     * @return
     */
    MemberVo findMemberById(Long id);

    /**
     * 添加会员
     * @param member
     */
    void addMember(MemberVo member);

    /**
     * 修改会员信息
     * @param member
     */
    void updateMember(MemberVo member);

    /**
     * 删除会员
     * @param id
     */
    void deleteMember(Long id);

    /**
     * 分页查询会员
     * @param memberDto
     * @return
     */
    List<MemberVo> queryMember(MemberDto memberDto);

    /**
     * 多态修改会员信息
     * @param memberList
     */
    void updateMembers(List<MemberVo> memberList);

    /**
     * 批量删除会员
     * @param idList
     */
    void deleteMembers(List<Long> idList);
}