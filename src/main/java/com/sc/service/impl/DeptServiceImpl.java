package com.sc.service.impl;

import com.sc.mapper.DeptMapper;
import com.sc.pojo.Dept;
import com.sc.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public Dept selectById(Integer deptId) {
        return deptMapper.selectById(deptId);
    }
}
