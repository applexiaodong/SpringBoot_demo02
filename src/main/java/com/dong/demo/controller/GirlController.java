package com.dong.demo.controller;

import com.dong.demo.domain.Girl;
import com.dong.demo.domain.ResultMessage;
import com.dong.demo.service.GirlService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/girl")
public class GirlController {

    private final static Logger logger = LoggerFactory.getLogger(GirlController.class);

    @Autowired
    private GirlService girlService;

    @RequestMapping(value = "/add",produces = "application/json;charset=UTF-8")
    public ResultMessage<Girl> addGirl(@Valid Girl girl, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return null;
//            return ResultMessage.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultMessage.success(girlService.addGirl(girl));
    }

    @RequestMapping(value = "/list",produces = "application/json;charset=UTF-8")
    public ResultMessage findAll(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "2") int pageSize){
        ResultMessage result = new ResultMessage();
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<Girl> pageInfo = new PageInfo<>(girlService.findAll());
        result.setCode(0);
        result.setMsg("OK");
        result.setCount(pageInfo.getTotal());
        result.setData(pageInfo.getList());
        return result;

    }

    @PostMapping(value = "query")
    public ResultMessage<List<Girl>> findAll(@RequestBody Girl girl){
        List<Girl> girls = new ArrayList<>();
        Girl outGirl = girlService.selectSingleGirl(girl);
        girls.add(outGirl);
        return ResultMessage.success(girls);
    }

    @GetMapping(value = "/getAge/{id}")
    public void getAge(@PathVariable int id) throws Exception{
        girlService.selectAgeById(id);
    }

    @RequestMapping(value = "/hello")
    public String hello(){
        return "Nice to meet you";
    }
}
