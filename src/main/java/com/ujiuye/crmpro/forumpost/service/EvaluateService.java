package com.ujiuye.crmpro.forumpost.service;

import com.ujiuye.crmpro.forumpost.pojo.Evaluate;

import java.util.List;

public interface EvaluateService {
    //1、列表
    List<Evaluate> list(int forumFk);
    //2、发表评论（更新评论数量）
    int save(Evaluate evaluate);
}
