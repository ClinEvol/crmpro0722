package com.ujiuye.crmpro.task.service;

import com.ujiuye.crmpro.employee.pojo.Employee;
import com.ujiuye.crmpro.employee.service.EmployeeService;
import com.ujiuye.crmpro.task.mapper.TaskMapper;
import com.ujiuye.crmpro.task.pojo.Task;
import com.ujiuye.crmpro.task.pojo.TaskExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private EmployeeService employeeService;

    @Override
    public List<Task> list(int type, String keyword,int emp_fk) {
        TaskExample example=new TaskExample();
        TaskExample.Criteria criteria = example.createCriteria();
        List<Integer> ids=new ArrayList<>();
        ids.add(0);

        if(emp_fk!=0){//代表查我的任务
            criteria.andEmpFk2EqualTo(emp_fk);
        }
        if(type==1){//任务标题
            criteria.andTasktitleLike("%"+keyword+"%");
        }
        if(type==2 || type==3){//查询分配者或执行者的id
            List<Employee> employees = employeeService.listByName(keyword);
            if(employees!=null && employees.size()>0){
                for(Employee employee:employees){
                    ids.add(employee.getEid());
                }
            }
        }
        if(type==2){//按执行者找
            criteria.andEmpFk2In(ids);
        }
        if(type==3){//按分配者找
            criteria.andEmpFkIn(ids);
        }
        return taskMapper.selectByExample(example);
    }

    @Override
    public int save(Task task) {
        return taskMapper.insert(task);
    }

    @Override
    public int update(Task task) {
        return taskMapper.updateByPrimaryKeySelective(task);
    }
}
