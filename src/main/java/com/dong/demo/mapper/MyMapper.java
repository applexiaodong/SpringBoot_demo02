package com.dong.demo.mapper;

import com.github.pagehelper.Page;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @description 自定义mapper
 * @author: wangxd
 * @create: 2019-06-19
 **/
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
    //该接口不能被扫描到，否则会出错

}
