package com.sc.service;

import com.sc.pojo.Dept;

import java.util.List;

public interface DeptService {
    Dept selectById(Integer deptId);

    List<Dept> list();
}
