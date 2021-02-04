package com.ujiuye.crmpro.project.service;

import com.ujiuye.crmpro.project.pojo.Analysis;
import com.ujiuye.crmpro.project.pojo.Project;

import java.util.List;

public interface AnalysisService {
    Analysis getById(int id);

    List<Analysis> list(int type, String keyword);

    int save(Analysis analysis);



}
