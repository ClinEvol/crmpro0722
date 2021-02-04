package com.ujiuye.crmpro.project.service;

import com.ujiuye.crmpro.project.mapper.FunctionMapper;
import com.ujiuye.crmpro.project.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FunctionServiceImpl implements FunctionService {
    @Autowired
    private FunctionMapper functionMapper;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private AnalysisService analysisService;
    @Autowired
    private ProjectService projectService;

    @Override
    public List<Function> list(int type, String keyword) {
        FunctionExample example=new FunctionExample();
        FunctionExample.Criteria criteria = example.createCriteria();
        List<Integer> ids=new ArrayList<>();
        ids.add(0);
        if(type==1){//功能名称
            criteria.andFunctionnameLike("%"+keyword+"%");
        }
        if(type==2){//模块名称
            List<Module> list = moduleService.list(1, keyword);
            if(list!=null && list.size()>0){
                for (Module module:list){
                    ids.add(module.getId());
                }
            }
            criteria.andModuleFkIn(ids);
        }
        if(type==3){//需求名称
            List<Analysis> list = analysisService.list(1, keyword);
            if(list!=null && list.size()>0){
                for (Analysis analysis:list){
                    ids.add(analysis.getId());
                }
            }
            criteria.andAnalysisFkIn(ids);
        }
        if(type==4){//项目名称
            List<Project> list = projectService.list(1, keyword);
            if(list!=null && list.size()>0){
                for (Project project:list){
                    ids.add(project.getPid());
                }
            }
            criteria.andProFkIn(ids);
        }
        return functionMapper.selectByExample(example);
    }

    @Override
    public int save(Function function) {
        return functionMapper.insert(function);
    }

    @Override
    public List<Function> list(int module_id) {
        FunctionExample example=new FunctionExample();
        FunctionExample.Criteria criteria = example.createCriteria();
        criteria.andModuleFkEqualTo(module_id);
        return functionMapper.selectByExample(example);
    }
}
