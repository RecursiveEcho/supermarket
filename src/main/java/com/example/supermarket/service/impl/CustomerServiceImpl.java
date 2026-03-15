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
        return customerMapper.findCustomerById(id);
    }

    @Override
    public void addCustomer(CustomerVo customer) {
        customerMapper.addCustomer(customer);
    }

    @Override
    public void updateCustomer(CustomerVo customer) {
        customerMapper.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
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
        customerMapper.deleteCustomers(idList);
    }
}
