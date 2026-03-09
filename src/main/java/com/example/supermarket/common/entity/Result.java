package com.example.supermarket.common.entity;

import lombok.Data;

/**
 * 结果实体类
 * <p>
 * 结果实体类
 *</p>
 * @author chen.yang
 * @date 2026-03-09 11:40:14
 */
@Data
public class Result {
 private Integer code;//状态码,1为成功，0为失败
 private  String msg;//错误信息
 private Object data;//数据
    public static Result success(){
        Result result = new Result();
        result.code=1;
        result.msg="success";
        return result;
    }
    public static Result success(Object object){
        Result result = new Result();
        result.code =1;
        result.data =object;
        result.msg="success";
        return result;
    }
    public static Result error(String msg){
        Result result = new Result();
        result.code =0;
        result.msg =msg;
        return result;
    }
}
