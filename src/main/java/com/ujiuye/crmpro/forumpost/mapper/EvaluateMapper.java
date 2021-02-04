package com.ujiuye.crmpro.forumpost.mapper;

import com.ujiuye.crmpro.forumpost.pojo.Evaluate;
import com.ujiuye.crmpro.forumpost.pojo.EvaluateExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluateMapper {
    int countByExample(EvaluateExample example);

    int deleteByExample(EvaluateExample example);

    int deleteByPrimaryKey(Integer evaid);

    int insert(Evaluate record);

    int insertSelective(Evaluate record);

    List<Evaluate> selectByExample(EvaluateExample example);

    Evaluate selectByPrimaryKey(Integer evaid);

    int updateByExampleSelective(@Param("record") Evaluate record, @Param("example") EvaluateExample example);

    int updateByExample(@Param("record") Evaluate record, @Param("example") EvaluateExample example);

    int updateByPrimaryKeySelective(Evaluate record);

    int updateByPrimaryKey(Evaluate record);
}