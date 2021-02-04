package com.ujiuye.crmpro.employee.service;

import com.ujiuye.crmpro.employee.mapper.EmployeeMapper;
import com.ujiuye.crmpro.employee.pojo.Employee;
import com.ujiuye.crmpro.employee.pojo.EmployeeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> listByName(String name) {
        EmployeeExample example=new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEnameLike("%"+name+"%");
        return employeeMapper.selectByExample(example);
    }

    @Override
    public List<Employee> listByPosition(int position) {
        EmployeeExample example=new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andPFkEqualTo(position);
        return employeeMapper.selectByExample(example);
    }

    @Override
    public Employee login(Employee employee) {
        EmployeeExample example=new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(employee.getUsername());
        criteria.andPasswordEqualTo(employee.getPassword());
        List<Employee> employees = employeeMapper.selectByExample(example);
        if(employees!=null && employees.size()>0){
            return employees.get(0);
        }
        return null;
    }

    @Override
    public Employee getByName(String name) {
        EmployeeExample example=new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEnameEqualTo(name);
        List<Employee> employees = employeeMapper.selectByExample(example);
        if(employees!=null && employees.size()>0){
            return employees.get(0);
        }
        return null;
    }
}
