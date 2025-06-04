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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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

    // 根据条件查询
    @Override
    public PageResult<Emp> queryCondition(PageParams<Emp> params) {
        // 分页查询
        Page<Emp> page = PageHelper.startPage(params.getPageNum(), params.getPageSize());
        log.info("page为：{}",page);
        // 条件查询;params为查询条件
        List<Emp> empList = empMapper.queryCondition(params.getParams());
        log.info("查询结果为：{}",empList);
        for (Emp emp : empList) {
            Dept dept = deptMapper.selectById(emp.getDept_id());
            emp.setDept(dept);
        }

        return new PageResult<>(page.getTotal(), empList);
    }

    /**
     * 添加员工
     * @param emp
     */
    @Override
    public void add(Emp emp) {
       empMapper.add(emp);
    }

    @Override
    public void update(Emp emp) {
        empMapper.update(emp);
    }


//    @Override
//    public List<Emp> queryAll() {
//        return empMapper.queryAll(params);
//    }
}
