package com.ujiuye.crmpro.project.service;

import com.ujiuye.crmpro.project.pojo.Project;

import java.util.List;

public interface ProjectService {

    List<Project> list(int type,String keyword);

    int save(Project project);

    int removeById(int id);
    int remove(List<Integer> ids);

    int update(Project project);
    Project getById(int id);
}
