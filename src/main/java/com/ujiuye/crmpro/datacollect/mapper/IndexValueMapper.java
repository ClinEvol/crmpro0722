package com.ujiuye.crmpro.datacollect.mapper;

import com.ujiuye.crmpro.datacollect.pojo.IndexValue;
import com.ujiuye.crmpro.datacollect.pojo.IndexValueExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IndexValueMapper {
    int countByExample(IndexValueExample example);

    int deleteByExample(IndexValueExample example);

    int deleteByPrimaryKey(Integer inId);

    int insert(IndexValue record);

    int insertSelective(IndexValue record);

    List<IndexValue> selectByExample(IndexValueExample example);

    IndexValue selectByPrimaryKey(Integer inId);

    int updateByExampleSelective(@Param("record") IndexValue record, @Param("example") IndexValueExample example);

    int updateByExample(@Param("record") IndexValue record, @Param("example") IndexValueExample example);

    int updateByPrimaryKeySelective(IndexValue record);

    int updateByPrimaryKey(IndexValue record);
}