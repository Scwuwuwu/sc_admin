package com.sc.controller;

import com.sc.common.JsonCode;
import com.sc.common.PageParams;
import com.sc.common.PageResult;
import com.sc.pojo.Emp;
import com.sc.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmpService empService;


//    @RequestMapping("/queryAll")
//    public List<Emp> queryAll(){
//        return empService.queryAll();
//    }
    /*
    * 分页查询
    * 前端: 发送参数
    * 1. pageNum: 当前页码
    * 2. pageSize: 每页显示条数
    *
    * 后端: 返回数据
    * 1. list: 当前页数据
    * 2. total: 总记录数
    * */
    @PostMapping("/queryPage")
    public PageResult<Emp> queryPage(@RequestBody PageParams<Emp>  params){
        PageResult<Emp> pageList = empService.queryPage(params);
        return pageList;
    }

    @DeleteMapping("/delete/{id}")
    public JsonCode delete(@PathVariable("id") Integer id){
        empService.deleteById(id);
        return new JsonCode(100, "删除成功",null);
    }
}
