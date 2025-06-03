package com.sc.mapper;

import com.sc.common.PageParams;
import com.sc.pojo.Emp;

import java.util.List;

public interface EmpMapper {
    List<Emp> queryAll(PageParams<Emp> params);

    void deleteById(Integer id);

    List<Emp> queryCondition(Emp params);
}
