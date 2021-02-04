package com.ujiuye.crmpro.task.service;

import com.ujiuye.crmpro.task.pojo.Task;

import java.util.List;

public interface TaskService {
    List<Task> list(int type, String keyword,int emp_fk);
    int save(Task task);
    int update(Task task);
}
