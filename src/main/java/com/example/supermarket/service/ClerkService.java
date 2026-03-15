package com.example.supermarket.service;

import com.example.supermarket.pojo.VO.ClerkVo;
import com.example.supermarket.pojo.entity.PageResult;

import java.util.List;

/**
 * 实现功能【】
 * <p>
 *
 * </p>
 *
 * @author chen.yang
 * @date 2026-03-13 19:03:53
 */
public interface ClerkService {

    List<ClerkVo> findAllClerk();

    ClerkVo findClerkById(Long id);

    void addClerk(ClerkVo clerk);

    void updateClerk(ClerkVo clerk);

    void deleteClerk(Long id);

    PageResult<ClerkVo> findClerkByPage(Integer pageNum, Integer pageSize);

    List<ClerkVo> queryClerk(ClerkVo clerkDto);

    void updateClerks(List<ClerkVo> clerkList);

    void deleteClerks(List<Long> idList);
}