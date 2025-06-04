package com.sc.mapper;

import com.sc.pojo.Dept;

import java.util.List;

public interface DeptMapper {
    Dept selectById(Integer deptId);

    List<Dept> list();
}
