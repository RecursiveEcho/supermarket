package com.example.supermarket.service.impl;



import com.example.supermarket.pojo.DTO.CustomerDto;
import com.example.supermarket.pojo.VO.CustomerVo;
import com.example.supermarket.pojo.entity.PageResult;
import com.example.supermarket.mapper.CustomerMapper;
import com.example.supermarket.service.CustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 实现功能【】
 * <p>
 *
 * </p>
 *
 * @author chen.yang
 * @date 2026-03-15 10:20:19
 */
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<CustomerVo> findAllCustomer() {
        return customerMapper.findAllCustomer();
    }

    @Override
    public CustomerVo findCustomerById(Long id) {
        CustomerVo customer = customerMapper.findCustomerById(id);
        if (customer != null) {
            return customer;
        }
        throw new IllegalArgumentException("查询失败：客户不存在，ID: " + id);
    }

    @Override
    public void addCustomer(CustomerVo customer) {
        customerMapper.addCustomer(customer);
    }

    @Override
    public void updateCustomer(CustomerVo customer) {
        // 先检查客户是否存在
        CustomerVo existingCustomer = customerMapper.findCustomerById(customer.getId());
        if (existingCustomer == null) {
            throw new IllegalArgumentException("更新失败：客户不存在，ID: " + customer.getId());
        }
        customerMapper.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        // 先检查客户是否存在
        CustomerVo customer = customerMapper.findCustomerById(id);
        if (customer == null) {
            throw new IllegalArgumentException("删除失败：客户不存在，ID: " + id);
        }
        customerMapper.deleteCustomer(id);
    }

    @Override
    public PageResult<CustomerVo> findCustomerByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CustomerVo> customerList = customerMapper.findAllCustomer();

        PageInfo<CustomerVo> pageInfo = new PageInfo<>(customerList);

        return PageResult.of(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public List<CustomerVo> queryCustomer(CustomerDto customerDto) {
        return customerMapper.queryCustomer(customerDto);
    }

    @Override
    public void updateCustomers(List<CustomerVo> customerList) {
        customerMapper.updateCustomers(customerList);
    }

    @Override
    public void deleteCustomers(List<Long> idList) {
        for (Long id : idList){
            CustomerVo customer = customerMapper.findCustomerById(id);
            if (customer == null) {
                throw new IllegalArgumentException("删除失败：客户不存在，ID: " + id);
            }
        }
        customerMapper.deleteCustomers(idList);
    }
}