package com.ujiuye.crmpro.forumpost.service;

import com.ujiuye.crmpro.forumpost.pojo.Forumsort;

import java.util.List;

public interface ForumsortService {
    // 1、版块列表
    List<Forumsort> list();
    //2、更新版块的贴子数量
    int updateCount(int id);
    //3、查看贴子列表（更新浏览量）
    int updateClick(int id);
}
