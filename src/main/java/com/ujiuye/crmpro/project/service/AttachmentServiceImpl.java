package com.ujiuye.crmpro.project.service;

import com.ujiuye.crmpro.project.mapper.AttachmentMapper;
import com.ujiuye.crmpro.project.pojo.Attachment;
import com.ujiuye.crmpro.project.pojo.AttachmentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttachmentServiceImpl implements AttachmentService {
    @Autowired
    private AttachmentMapper attachmentMapper;

    @Override
    public List<Attachment> listByProFk(int pro_fk) {
        AttachmentExample example=new AttachmentExample();
        AttachmentExample.Criteria criteria = example.createCriteria();
        criteria.andProFkEqualTo(pro_fk);
        return attachmentMapper.selectByExample(example);
    }

    @Override
    public List<Attachment> list(int type, String tag, String keyword) {
        AttachmentExample example=new AttachmentExample();
        AttachmentExample.Criteria criteria = example.createCriteria();
        if(type!=0){//1 表示图片,2 表示文档,3 表示视频,4 表示种子,5 表示音乐,6表示其它
            criteria.andTypeEqualTo(type);
        }
        if(tag!=null && !tag.equals("")){
            criteria.andTagLike("%"+tag+"%");
        }
        if(keyword!=null && !keyword.equals("")){
            criteria.andAttnameLike("%"+keyword+"%");
        }
        return attachmentMapper.selectByExample(example);
    }

    @Override
    public int save(Attachment attachment) {
        return attachmentMapper.insert(attachment);
    }

    @Override
    public Attachment getById(int id) {
        return attachmentMapper.selectByPrimaryKey(id);
    }
}
