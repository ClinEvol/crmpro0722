package com.ujiuye.crmpro.message.service;

import com.ujiuye.crmpro.message.pojo.Message;

import java.util.List;

public interface MessageService {
    //收件箱，已读和未读
    List<Message> listInbox(int type,int receiveId);
    //
    int save(Message message);
    int update(Message message);
    Message getById(int id);
}
