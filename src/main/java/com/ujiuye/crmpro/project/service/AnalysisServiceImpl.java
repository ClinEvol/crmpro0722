package com.ujiuye.crmpro.project.service;

import com.ujiuye.crmpro.project.mapper.AnalysisMapper;
import com.ujiuye.crmpro.project.pojo.Analysis;
import com.ujiuye.crmpro.project.pojo.AnalysisExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalysisServiceImpl implements AnalysisService {
    @Autowired
    private AnalysisMapper analysisMapper;

    @Override
    public Analysis getById(int id) {
        return analysisMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Analysis> list(int type, String keyword) {
        AnalysisExample example=new AnalysisExample();
        AnalysisExample.Criteria criteria = example.createCriteria();
        if(type==1){//按标题
            criteria.andTitleLike("%"+keyword+"%");
        }
        if(type==2){//按项目名称
            criteria.andPronameLike("%"+keyword+"%");
        }
        return analysisMapper.selectByExample(example);
    }

    @Override
    public int save(Analysis analysis) {
        return analysisMapper.insert(analysis);
    }
}
