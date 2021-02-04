package com.ujiuye.crmpro.employee.service;

import com.ujiuye.crmpro.customer.pojo.Customer;
import com.ujiuye.crmpro.employee.pojo.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> listByName(String name);
    //通过职位查员工信息
    List<Employee> listByPosition(int position);

    Employee login(Employee employee);

    Employee getByName(String name);
}
