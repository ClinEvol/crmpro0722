package com.ujiuye.crmpro.forumpost.service;

import com.ujiuye.crmpro.forumpost.mapper.ForumpostMapper;
import com.ujiuye.crmpro.forumpost.pojo.Forumpost;
import com.ujiuye.crmpro.forumpost.pojo.ForumpostExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumpostServiceImpl implements ForumpostService {
    @Autowired
    private ForumpostMapper forumpostMapper;
    @Autowired
    private ForumsortService forumsortService;

    @Override
    public List<Forumpost> list(int forumsortFk) {
        ForumpostExample example=new ForumpostExample();
        ForumpostExample.Criteria criteria = example.createCriteria();
        criteria.andForumsortFkEqualTo(forumsortFk);
        return forumpostMapper.selectByExample(example);
    }

    @Override
    public Forumpost getById(int forumid) {
        return forumpostMapper.selectByPrimaryKey(forumid);
    }

    @Override
    public int save(Forumpost forumpost) {
        //更新版块的贴子数量
        forumsortService.updateCount(forumpost.getForumsortFk());
        return forumpostMapper.insert(forumpost);
    }

    @Override
    public int updateCommentCount(int forumid) {
        Forumpost forumpost = forumpostMapper.selectByPrimaryKey(forumid);
        forumpost.setCommentCount(forumpost.getCommentCount()+1);
        return forumpostMapper.updateByPrimaryKeySelective(forumpost);
    }
}
