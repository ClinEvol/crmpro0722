package com.ujiuye.crmpro.system.service;

import com.ujiuye.crmpro.system.mapper.SourcesMapper;
import com.ujiuye.crmpro.system.pojo.Sources;
import com.ujiuye.crmpro.system.pojo.SourcesExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SourcesServiceImpl implements SourcesService {

    @Autowired
    private SourcesMapper sourcesMapper;

    @Override
    @Cacheable(key = "'list'",value = "list")
    public List<Sources> list() {
        //获取根节点
        SourcesExample example=new SourcesExample();
        SourcesExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(0);
        List<Sources> sourcess = sourcesMapper.selectByExample(example);
        //判断是否存在根节点
        if(sourcess!=null && sourcess.size()>0){
            for(Sources sources:sourcess){
                findChildren(sources);
            }
        }
        return sourcess;
    }

    @Override
    @Cacheable(key = "'listAll'",value = "listAll")
    public List<Sources> listAll() {
        return sourcesMapper.selectByExample(null);
    }

    //oa办公协同系统   sources
    private void findChildren(Sources sources){
        SourcesExample example=new SourcesExample();
        SourcesExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(sources.getId());
        //sourcess 项目管理 日常办公  发送邮件
        List<Sources> sourcess = sourcesMapper.selectByExample(example);
        //判断是否存在子节点
        if(sourcess!=null && sourcess.size()>0){
            //有节点  存起来
            sources.setChildren(sourcess);
            for(Sources sourcesFor:sourcess){
                //要查这个节点的子节点
                findChildren(sourcesFor );//递归
            }
        }
    }

    @Override
    @Cacheable(key = "'getById_'+#id",value = "getById")
    public Sources getById(int id) {
        return sourcesMapper.selectByPrimaryKey(id);
    }

    @Override
    @CacheEvict(value = {"getById","listAll","list"},allEntries = true)
    public int save(Sources sources) {
        return sourcesMapper.insert(sources);
    }

    @Override
    @CacheEvict(value = {"getById","listAll","list"},allEntries = true)
    public int update(Sources sources) {
        return sourcesMapper.updateByPrimaryKeySelective(sources);
    }

    @Override
    @CacheEvict(value = {"getById","listAll","list"},allEntries = true)
    public int remove(int id) {
        return sourcesMapper.deleteByPrimaryKey(id);
    }
}
