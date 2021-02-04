package com.ujiuye.crmpro.project.service;

import com.ujiuye.crmpro.project.pojo.Analysis;
import com.ujiuye.crmpro.project.pojo.Module;

import java.util.List;

public interface ModuleService {
    List<Module> list(int type, String keyword);

    int save(Module module);

    List<Module> listByAnalysisFk(int analysis_fk);

}
