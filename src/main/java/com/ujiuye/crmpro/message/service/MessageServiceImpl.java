package com.ujiuye.crmpro.message.service;

import com.ujiuye.crmpro.message.mapper.MessageMapper;
import com.ujiuye.crmpro.message.pojo.Message;
import com.ujiuye.crmpro.message.pojo.MessageExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public List<Message> listInbox(int type,int receiveId) {
        MessageExample example=new MessageExample();
        example.setOrderByClause("status desc");
        MessageExample.Criteria criteria = example.createCriteria();
        //查未读和已读
        List<Integer> status=new ArrayList<>();
        status.add(0);
        status.add(1);
        criteria.andStatusIn(status);
        //按类型
        if(type<4){
            criteria.andTypeEqualTo(type);
        }
        //查当前用户的
        criteria.andReceiveEqualTo(receiveId);
        return messageMapper.selectByExample(example);
    }

    @Override
    public int save(Message message) {
        return messageMapper.insert(message);
    }

    @Override
    public int update(Message message) {
        return messageMapper.updateByPrimaryKeySelective(message);
    }

    @Override
    public Message getById(int id) {
        return messageMapper.selectByPrimaryKey(id);
    }
}
