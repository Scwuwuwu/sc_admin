package com.sc.service;

import com.sc.common.PageParams;
import com.sc.common.PageResult;
import com.sc.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpService {
//    List<Emp> queryAll();

    PageResult<Emp> queryPage(PageParams<Emp> params);

    void deleteById(Integer id);

    PageResult<Emp> queryCondition(PageParams<Emp> params);

}
