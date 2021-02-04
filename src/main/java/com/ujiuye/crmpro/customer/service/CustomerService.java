package com.ujiuye.crmpro.customer.service;

import com.ujiuye.crmpro.customer.pojo.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> list(int type,String keyword);

    //添加
    int save(Customer customer);

    int update(Customer customer);
    //删除单个
    int removeById(int id);
    //批量删除
    int remove(List<Integer> ids);

    Customer getById(int id);

    List<Customer> listByName(String name);
}
