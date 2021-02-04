package com.ujiuye.crmpro.forumpost.service;

import com.ujiuye.crmpro.forumpost.pojo.Forumpost;

import java.util.List;
//帖子
public interface ForumpostService {
    //1，列表（根据版块分类）
    List<Forumpost> list(int forumsortFk);
    // 2、查看详情时（更新浏览量）---显示评论列表
    Forumpost getById(int forumid);
    //3、发贴（更新版块的贴子数量）
    int save(Forumpost forumpost);
    // 4、更新评论数量
    int updateCommentCount(int forumid);

}
