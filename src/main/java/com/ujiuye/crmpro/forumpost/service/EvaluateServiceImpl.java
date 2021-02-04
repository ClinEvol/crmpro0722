package com.ujiuye.crmpro.forumpost.service;

import com.ujiuye.crmpro.forumpost.mapper.EvaluateMapper;
import com.ujiuye.crmpro.forumpost.pojo.Evaluate;
import com.ujiuye.crmpro.forumpost.pojo.EvaluateExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EvaluateServiceImpl implements EvaluateService {
    @Autowired
    private EvaluateMapper evaluateMapper;
    @Autowired
    private ForumpostService forumpostService;

    @Override
    public List<Evaluate> list(int forumFk) {
        EvaluateExample example=new EvaluateExample();
        EvaluateExample.Criteria criteria = example.createCriteria();
        criteria.andForumFkEqualTo(forumFk);
        return evaluateMapper.selectByExample(example);
    }

    @Override
    public int save(Evaluate evaluate) {
        //更新评论数量
        forumpostService.updateCommentCount(evaluate.getForumFk());
        return evaluateMapper.insert(evaluate);
    }
}
