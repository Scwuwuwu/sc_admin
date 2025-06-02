package com.sc.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {;
    private Integer emp_id;
    private String emp_name;
    private Integer emp_sex;
    private String emp_address;
    private String emp_age;
    private BigDecimal emp_salary;
    // 时间格式化
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date emp_birth;
    private Integer dept_id;
    private Dept dept;  // 每一个部门id对应一个部门对象
}
