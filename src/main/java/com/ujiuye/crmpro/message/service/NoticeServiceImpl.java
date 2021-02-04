package com.ujiuye.crmpro.message.service;

import com.ujiuye.crmpro.message.mapper.NoticeMapper;
import com.ujiuye.crmpro.message.pojo.Notice;
import com.ujiuye.crmpro.message.pojo.NoticeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public List<Notice> list(String keyword) {
        NoticeExample example=new NoticeExample();
        NoticeExample.Criteria criteria = example.createCriteria();
        if(keyword!=null && !keyword.equals("")){
            criteria.andTitleLike("%"+keyword+"%");
        }
        return noticeMapper.selectByExample(example);
    }

    @Override
    public int save(Notice notice) {
        return noticeMapper.insert(notice);
    }

    @Override
    public Notice getById(int id) {
        return noticeMapper.selectByPrimaryKey(id);
    }
}
