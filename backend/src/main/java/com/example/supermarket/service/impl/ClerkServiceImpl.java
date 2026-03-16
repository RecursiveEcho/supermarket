package com.example.supermarket.service.impl;

import com.example.supermarket.pojo.VO.ClerkVo;
import com.example.supermarket.pojo.entity.PageResult;
import com.example.supermarket.mapper.ClerkMapper;
import com.example.supermarket.service.ClerkService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *员工实现类
 * @author chen.yang
 * @date 2026-03-13 19:04:19
 */
@Slf4j
@Service
public class ClerkServiceImpl implements ClerkService {

    @Autowired
    private ClerkMapper clerkMapper;

    /**
     * 查询所有员工
     * @return
     */
    @Operation(summary="查询员工",description="查询所有员工")
    @Override
    public List<ClerkVo> findAllClerk() {
        return clerkMapper.findAllClerk();
    }

    @Operation(summary="查询员工",description="根据id查询员工")
    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    @Override
    public ClerkVo findClerkById(Long id) {
        ClerkVo clerk = clerkMapper.findClerkById(id);
        if (clerk != null) {
            return clerk;
        }
        throw new IllegalArgumentException("查询失败：员工不存在，ID: " + id);
    }

    /**
     * 添加员工
     * @param clerk
     */
    @Override
    public void addClerk(ClerkVo clerk) {
       clerkMapper.addClerk(clerk);
    }

    /**
     * 修改员工信息
     * @param clerk
     */
    @Override
    public void updateClerk(ClerkVo clerk) {
        // 先检查员工是否存在
        ClerkVo existingClerk = clerkMapper.findClerkById(clerk.getId());
        if (existingClerk == null) {
            throw new IllegalArgumentException("更新失败：员工不存在，ID: " + clerk.getId());
        }
        clerkMapper.updateClerk(clerk);
    }

    /**
     * 删除员工
     * @param id
     */
    @Override
    public void deleteClerk(Long id) {
        // 先检查员工是否存在
        ClerkVo clerk = clerkMapper.findClerkById(id);
        if (clerk == null) {
            throw new IllegalArgumentException("删除失败：员工不存在，ID: " + id);
        }
        clerkMapper.deleteClerk(id);
    }

    /**
     * 分页查询员工
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageResult<ClerkVo> findClerkByPage(Integer pageNum, Integer pageSize) {
        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        List<ClerkVo> clerkList = clerkMapper.findAllClerk();
        
        // 使用 PageInfo 包装查询结果
        PageInfo<ClerkVo> pageInfo = new PageInfo<>(clerkList);
        
        // 使用静态工厂方法构建分页结果
        return PageResult.of(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public List<ClerkVo> queryClerk(ClerkVo clerkDto) {
        log.info("Service 层接收到的参数：id={}, username={}, name={}", 
            clerkDto.getId(), clerkDto.getUserName(), clerkDto.getName());
        return clerkMapper.queryClerk(clerkDto);
    }

    @Override
    public void updateClerks(List<ClerkVo> clerkList) {
        clerkMapper.updateClerks(clerkList);
    }

    @Override
    public void deleteClerks(List<Long> idList) {
        // 检查是否有不存在的员工
        for (Long id : idList) {
            ClerkVo clerk = clerkMapper.findClerkById(id);
            if (clerk == null) {
                throw new IllegalArgumentException("删除失败：员工不存在，ID: " + id);
            }
        }
        clerkMapper.deleteClerks(idList);
    }
}