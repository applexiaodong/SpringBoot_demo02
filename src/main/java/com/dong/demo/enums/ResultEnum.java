package com.dong.demo.enums;

/**
 * @description
 * @author: wangxd
 * @create: 2019-08-27
 **/
public enum ResultEnum {
    UNKNOWN_ERROR(-1,"系统未知错误"),
    SUCCESS(0,"操作成功"),
    PRIMARY_SCHOOL(100,"你是小学生"),
    MIDDLE_SCHOOL(101,"你是中学生"),
    COLLAGE_SCHOOL(102,"你是大学生"),
    FILE_NOT_EMPTY(300,"上传图片不能为空"),
    FILE_UPLOAD_SUCCESS(301,"图片上传成功"),
    FILE_UPLOAD_FAIL(302,"图片上传失败"),
    FILE_UPLOAD_TOO_LARGE(303,"图片太大"),;


    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
