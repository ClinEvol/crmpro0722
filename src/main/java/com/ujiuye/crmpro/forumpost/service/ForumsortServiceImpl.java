package com.ujiuye.crmpro.forumpost.service;

import com.ujiuye.crmpro.forumpost.mapper.ForumsortMapper;
import com.ujiuye.crmpro.forumpost.pojo.Forumsort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ForumsortServiceImpl implements ForumsortService {
    @Autowired
    private ForumsortMapper forumsortMapper;

    @Override
    public List<Forumsort> list() {
        return forumsortMapper.selectByExample(null);
    }

    @Override
    public int updateCount(int id) {
        //先查再改
        Forumsort forumsort = forumsortMapper.selectByPrimaryKey(id);
        forumsort.setCount(forumsort.getCount()+1);
        return forumsortMapper.updateByPrimaryKeySelective(forumsort);
    }

    @Override
    public int updateClick(int id) {
        //先查再改
        Forumsort forumsort = forumsortMapper.selectByPrimaryKey(id);
        forumsort.setClick(forumsort.getClick()+1);
        return forumsortMapper.updateByPrimaryKeySelective(forumsort);
    }
}
