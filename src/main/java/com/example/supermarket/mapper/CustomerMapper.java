package com.example.supermarket.mapper;

import com.example.supermarket.pojo.DTO.CustomerDto;
import com.example.supermarket.pojo.VO.CustomerVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 实现功能【】
 * <p>
 *
 * </p>
 *
 * @author chen.yang
 * @date 2026-03-15 10:18:49
 */
@Mapper
public interface CustomerMapper {

    /**
     * 查询所有客户
     * @return
     */
    List<CustomerVo> findAllCustomer();

    /**
     * 根据 id 查询客户
     * @param id
     * @return
     */
    CustomerVo findCustomerById(Long id);

    /**
     * 添加客户
     * @param customer
     */
    void addCustomer(CustomerVo customer);

    /**
     * 修改客户信息
     * @param customer
     */
    void updateCustomer(CustomerVo customer);

    /**
     * 删除客户
     * @param id
     */
    void deleteCustomer(Long id);

    /**
     * 分页查询客户
     * @param customerDto
     * @return
     */
    List<CustomerVo> queryCustomer(CustomerDto customerDto);

    /**
     * 批量修改客户信息
     * @param customerList
     */
    void updateCustomers(List<CustomerVo> customerList);

    /**
     * 批量删除客户
     * @param idList
     */
    void deleteCustomers(List<Long> idList);
}