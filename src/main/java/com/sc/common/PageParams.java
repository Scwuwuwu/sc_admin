package com.sc.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 封装接收前端的参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageParams<T> {
    private Integer pageNum = 1;    // 默认第一页
    private Integer pageSize = 5;   // 5条记录

    // 查询条件
    private T params;
}
