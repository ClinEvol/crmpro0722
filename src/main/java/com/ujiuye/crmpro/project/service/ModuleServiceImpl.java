package com.ujiuye.crmpro.project.service;

import com.ujiuye.crmpro.project.mapper.ModuleMapper;
import com.ujiuye.crmpro.project.pojo.Module;
import com.ujiuye.crmpro.project.pojo.ModuleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    private ModuleMapper moduleMapper;

    @Override
    public List<Module> list(int type, String keyword) {
        ModuleExample example=new ModuleExample();
        ModuleExample.Criteria criteria = example.createCriteria();
        if(type==1){//模块名称
            criteria.andModulenameLike("%"+keyword+"%");
        }
        if(type==2){//项目名称
            criteria.andProjectnameLike("%"+keyword+"%");
        }
        return moduleMapper.selectByExample(example);
    }

    @Override
    public int save(Module module) {
        return moduleMapper.insert(module);
    }

    @Override
    public List<Module> listByAnalysisFk(int analysis_fk) {
        ModuleExample example=new ModuleExample();
        ModuleExample.Criteria criteria = example.createCriteria();
        criteria.andAnalysisFkEqualTo(analysis_fk);
        return moduleMapper.selectByExample(example);
    }


}
