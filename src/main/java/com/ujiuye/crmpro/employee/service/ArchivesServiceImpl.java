package com.ujiuye.crmpro.employee.service;

import com.ujiuye.crmpro.employee.mapper.ArchivesMapper;
import com.ujiuye.crmpro.employee.pojo.Archives;
import com.ujiuye.crmpro.employee.pojo.ArchivesExample;
import com.ujiuye.crmpro.employee.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArchivesServiceImpl implements ArchivesService {
    @Autowired
    private ArchivesMapper archivesMapper;
    @Autowired
    private EmployeeService employeeService;

    @Override
    public Archives getByEmpFk(int emp_fk) {
        ArchivesExample example=new ArchivesExample();
        ArchivesExample.Criteria criteria = example.createCriteria();
        criteria.andEmpFkEqualTo(emp_fk);
        List<Archives> archives = archivesMapper.selectByExample(example);
        if(archives!=null && archives.size()>0){
            return archives.get(0);
        }
        return null;
    }

    @Override
    public List<Archives> list(int type, String keyword) {
        ArchivesExample example=new ArchivesExample();
        ArchivesExample.Criteria criteria = example.createCriteria();
        if(type==1){//按姓名
            List<Employee> employees = employeeService.listByName(keyword);
            List<Integer> ids=new ArrayList<>();
            ids.add(0);
            if(employees!=null && employees.size()>0){
                for (Employee employee:employees){
                    ids.add(employee.getEid());
                }
            }
            criteria.andEmpFkIn(ids);
        }
        if(type==2){//按学校
            criteria.andSchoolLike("%"+keyword+"%");
        }
        if(type==3){//按邮箱
            criteria.andEmailLike("%"+keyword+"%");
        }
        return archivesMapper.selectByExample(example);
    }

    @Override
    public int save(List<Archives> list) {
        return archivesMapper.save(list);
    }
}
