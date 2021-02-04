package com.ujiuye.crmpro.project.service;

import com.ujiuye.crmpro.project.pojo.Attachment;

import java.util.List;

public interface AttachmentService {
    List<Attachment> listByProFk(int pro_fk);

    //type文件类型   tag标签      keyword附件名称关键字
    List<Attachment> list(int type,String tag,String keyword);
    //添加
    int save(Attachment attachment);
    //获取单个对象   查看，修改
    Attachment getById(int id);
}
