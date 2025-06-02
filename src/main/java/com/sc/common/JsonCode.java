package com.sc.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonCode<T> {
    private  Integer code;
     private String msg;
      private T data;
}
