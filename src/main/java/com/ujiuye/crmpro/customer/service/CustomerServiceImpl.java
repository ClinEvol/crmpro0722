package com.ujiuye.crmpro.customer.service;

import com.ujiuye.crmpro.customer.mapper.CustomerMapper;
import com.ujiuye.crmpro.customer.pojo.Customer;
import com.ujiuye.crmpro.customer.pojo.CustomerExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<Customer> list(int type, String keyword) {
        CustomerExample example=new CustomerExample();
        CustomerExample.Criteria criteria=example.createCriteria();
        if (type==1){//按联系人查
            criteria.andCompanypersonLike("%"+keyword+"%");
        }else if(type==2){//按公司名称
            criteria.andComnameLike("%"+keyword+"%");
        }
        //如果前面条件都不成立，说明没有设定条件
        return customerMapper.selectByExample(example);
    }

    @Override
    public int save(Customer customer) {
        customer.setAddtime(new Date());
        return customerMapper.insert(customer);
    }

    @Override
    public int update(Customer customer) {
        return customerMapper.updateByPrimaryKeySelective(customer);
    }

    @Override
    public int removeById(int id) {
        return customerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int remove(List<Integer> ids) {
        CustomerExample example=new CustomerExample();
        CustomerExample.Criteria criteria=example.createCriteria();
        //where id in(1,2,3,4)
        criteria.andIdIn(ids);
        return customerMapper.deleteByExample(example);
    }



    @Override
    public Customer getById(int id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Customer> listByName(String name) {
        CustomerExample example=new CustomerExample();
        CustomerExample.Criteria criteria=example.createCriteria();
        criteria.andComnameLike("%"+name+"%");
        return customerMapper.selectByExample(example);
    }
}
