package com.ujiuye.crmpro.project.service;

import com.ujiuye.crmpro.customer.pojo.Customer;
import com.ujiuye.crmpro.customer.service.CustomerService;
import com.ujiuye.crmpro.employee.pojo.Employee;
import com.ujiuye.crmpro.employee.service.EmployeeService;
import com.ujiuye.crmpro.project.mapper.ProjectMapper;
import com.ujiuye.crmpro.project.pojo.Project;
import com.ujiuye.crmpro.project.pojo.ProjectExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private EmployeeService employeeService;

    @Override
    public List<Project> list(int type, String keyword) {
        ProjectExample projectExample=new ProjectExample();
        ProjectExample.Criteria criteria = projectExample.createCriteria();

        if(type==1){//项目名   在project表中
            criteria.andNameLike("%"+keyword+"%");
        }else if(type==2){//客户公司名称   在customer表中
            List<Customer> customers = customerService.listByName(keyword);
            List<Integer> ids=new ArrayList<>();
            ids.add(0);//先添加一个0，确不不会因为customers为空而出异常
            if(customers!=null){//如果集不为空说明有数据
                for(Customer customer:customers){
                    ids.add(customer.getId());
                }
            }
            criteria.andCompanynameIn(ids);//多个客户信息   in(1,2,3,5)
        }else if(type==3){//客户方负责人  在project表中
            criteria.andCompanypersonLike("%"+keyword+"%");
        }else if(type==4){//项目经理    在employee表中
            List<Employee> employees = employeeService.listByName(keyword);
            List<Integer> ids=new ArrayList<>();
            ids.add(0);//先添加一个0，确不不会因为customers为空而出异常
            if(employees!=null){//如果集不为空说明有数据
                for(Employee employee:employees){
                    ids.add(employee.getEid());
                }
            }
            criteria.andEmpFkIn(ids);//多个项目经理   in(1,2,3,5)
        }

        return projectMapper.selectByExample(projectExample);
    }

    @Override
    public int save(Project project) {

        return projectMapper.insert(project);
    }

    @Override
    public int removeById(int id) {
        return projectMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int remove(List<Integer> ids) {
        ProjectExample projectExample=new ProjectExample();
        ProjectExample.Criteria criteria = projectExample.createCriteria();
        criteria.andPidIn(ids);
        return projectMapper.deleteByExample(projectExample);
    }

    @Override
    public int update(Project project) {
        return projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public Project getById(int id) {
        return projectMapper.selectByPrimaryKey(id);
    }
}
