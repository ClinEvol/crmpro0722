package com.ujiuye.crmpro.project.service;

import com.ujiuye.crmpro.project.pojo.Function;

import java.util.List;

public interface FunctionService {
    List<Function> list(int type, String name);
    int save(Function function);

    List<Function> list(int module_id);
}
