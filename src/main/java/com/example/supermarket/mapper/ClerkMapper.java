package com.example.supermarket.mapper;

import com.example.supermarket.pojo.VO.ClerkVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 会员类接口
 * @author chen.yang
 * @date 2026-03-13 19:05:18
 */
@Mapper
public interface ClerkMapper {

    /**
     * `
     * @return
     */
    public List<ClerkVo> findAllClerk();

    /**
     * 根据id查询会员
     * @param id
     * @return
     */
    ClerkVo findClerkById(Long id);

    /**
     * 添加会员
     * @param clerk
     */
    void addClerk(ClerkVo clerk);

    /**
     * 修改会员信息
     * @param clerk
     */
    void updateClerk(ClerkVo clerk);

    /**
     * 删除会员
     * @param id
     */
    void deleteClerk(Long id);


    List<ClerkVo> queryClerk(ClerkVo clerkDto);

    void updateClerks(List<ClerkVo> clerkList);

    void deleteClerks(List<Long> idList);
}