package com.sc.controller;

import com.sc.common.JsonCode;
import com.sc.pojo.Dept;
import com.sc.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping("/list")
    public List<Dept> list() {
        return deptService.list();
    }
}
