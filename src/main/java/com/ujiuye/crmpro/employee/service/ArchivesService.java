package com.ujiuye.crmpro.employee.service;

import com.ujiuye.crmpro.employee.pojo.Archives;

import java.util.List;

public interface ArchivesService {
    Archives getByEmpFk(int emp_fk);

    List<Archives> list(int type, String keyword);
    int save(List<Archives> list);
}
