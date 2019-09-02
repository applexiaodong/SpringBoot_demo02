package com.dong.demo.Exception;

import com.dong.demo.enums.ResultEnum;

/**
 * @description
 * @author: wangxd
 * @create: 2019-08-27
 **/
public class GirlException extends RuntimeException{
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public GirlException(Integer code ,String message) {
        super(message);
        this.code = code;
    }

    public GirlException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
