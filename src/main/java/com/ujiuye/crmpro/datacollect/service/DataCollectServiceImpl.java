package com.ujiuye.crmpro.datacollect.service;

import com.ujiuye.crmpro.datacollect.mapper.DataCollectMapper;
import com.ujiuye.crmpro.datacollect.pojo.DataCollect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataCollectServiceImpl implements DataCollectService {
    @Autowired
    private DataCollectMapper dataCollectMapper;
    @Override
    public List<DataCollect> list() {
        return dataCollectMapper.selectByExample(null);
    }
}
