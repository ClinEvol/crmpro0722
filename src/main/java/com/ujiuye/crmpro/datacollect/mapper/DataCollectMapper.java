package com.ujiuye.crmpro.datacollect.mapper;

import com.ujiuye.crmpro.datacollect.pojo.DataCollect;
import com.ujiuye.crmpro.datacollect.pojo.DataCollectExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataCollectMapper {
    int countByExample(DataCollectExample example);

    int deleteByExample(DataCollectExample example);

    int deleteByPrimaryKey(Integer daid);

    int insert(DataCollect record);

    int insertSelective(DataCollect record);

    List<DataCollect> selectByExample(DataCollectExample example);

    DataCollect selectByPrimaryKey(Integer daid);

    int updateByExampleSelective(@Param("record") DataCollect record, @Param("example") DataCollectExample example);

    int updateByExample(@Param("record") DataCollect record, @Param("example") DataCollectExample example);

    int updateByPrimaryKeySelective(DataCollect record);

    int updateByPrimaryKey(DataCollect record);
}