package com.sc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sc.common.PageParams;
import com.sc.common.PageResult;
import com.sc.mapper.DeptMapper;
import com.sc.mapper.EmpMapper;
import com.sc.pojo.Dept;
import com.sc.pojo.Emp;
import com.sc.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public PageResult<Emp> queryPage(PageParams<Emp> params) {
        // 通过分页插件创建分页查询对象page
        Page<Emp> page = PageHelper.startPage(params.getPageNum(), params.getPageSize());
        // 查询所有记录
        List<Emp> list = empMapper.queryAll(params);
        for (Emp emp : list) {
            Dept dept = deptMapper.selectById(emp.getDept_id());
            emp.setDept(dept);
        }

        return new PageResult<>(page.getTotal(), page.getResult());
    }

    @Override
    public void deleteById(Integer id) {
        empMapper.deleteById(id);
    }


//    @Override
//    public List<Emp> queryAll() {
//        return empMapper.queryAll(params);
//    }
}
