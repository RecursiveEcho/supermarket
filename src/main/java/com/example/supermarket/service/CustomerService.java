package com.example.supermarket.service;

/**
 * 实现功能【】
 * <p>
 *
 * </p>
 *
 * @author chen.yang
 * @date 2026-03-15 10:19:50
 */

import com.example.supermarket.pojo.DTO.CustomerDto;
import com.example.supermarket.pojo.VO.CustomerVo;
import com.example.supermarket.pojo.entity.PageResult;

import java.util.List;

/**
 * 客户服务接口
 * @author chen.yang
 * @date 2026-03-15
 */
public interface CustomerService {
    List<CustomerVo> findAllCustomer();

    CustomerVo findCustomerById(Long id);

    void addCustomer(CustomerVo customer);

    void updateCustomer(CustomerVo customer);

    void deleteCustomer(Long id);

    PageResult<CustomerVo> findCustomerByPage(Integer pageNum, Integer pageSize);

    List<CustomerVo> queryCustomer(CustomerDto customerDto);

    void updateCustomers(List<CustomerVo> customerList);

    void deleteCustomers(List<Long> idList);
}
