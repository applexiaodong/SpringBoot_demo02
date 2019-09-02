package com.dong.demo.domain;

import com.dong.demo.enums.ResultEnum;
import lombok.Data;

@Data
public class ResultMessage<T> {

    private static String SUCCESS = "操作成功";
    private static String ERROR = "操作失败";
    private static int OK = 0;
    private static int FAILL = 1;

    //状态码
    private int code;

    //提示消息
    private String msg;

    //数量
    private Long count;

    //数据
    private T data;

    public static <T> ResultMessage<T> success(){
        return success(OK,SUCCESS,null);
    }

    public static <T> ResultMessage<T> success(T data){
        return success(OK,SUCCESS,data);
    }

    public static <T> ResultMessage<T> error(){
        return error(FAILL,ERROR,null);
    }

    public static <T> ResultMessage<T> error(String message){
        return error(FAILL,message,null);
    }

    public static <T> ResultMessage<T> error(ResultEnum resultEnum){
        return error(resultEnum.getCode(),resultEnum.getMsg(),null);
    }

    public static <T> ResultMessage<T> error(int code, String message){
        return error(code,message,null);
    }

    public static <T> ResultMessage<T> success(int code,String msg,T data){
        return new ResultMessage().data(data).code(code).msg(msg);
    }

    public static <T> ResultMessage<T> error(int code,String msg,T data){
        return new ResultMessage().data(data).code(code).msg(msg);
    }

    public ResultMessage<T> data(T data){
        this.data = data;
        return this;
    }

    public ResultMessage<T> msg(String msg){
        this.msg = msg;
        return this;
    }

    public ResultMessage<T> code(int code){
        this.code = code;
        return this;
    }




    @Override
    public String toString() {
        return "ResultMessage{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }
}
