package com.dong.demo.Exception;

import com.dong.demo.domain.ResultMessage;
import com.dong.demo.enums.ResultEnum;
import io.undertow.server.handlers.form.MultiPartParserDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description
 * @author: wangxd
 * @create: 2019-06-23
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    public  static final String GLOBAL_ERROR_VIEW = "error";

    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultMessage errorHandler(HttpServletRequest request, HttpServletResponse response,
                                              Exception e){
//        e.printStackTrace();
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("exception",e);
//        modelAndView.addObject("url",request.getRequestURL());
//        modelAndView.setViewName(GLOBAL_ERROR_VIEW);
//        return modelAndView;
        if (e instanceof GirlException){
            return ResultMessage.error(((GirlException) e).getCode(),e.getMessage());
        }else if (e instanceof MultiPartParserDefinition.FileTooLargeException){
            return ResultMessage.error(ResultEnum.FILE_UPLOAD_TOO_LARGE);
        }else{
            logger.info("Excetion:{}",e);
            return ResultMessage.error(ResultEnum.UNKNOWN_ERROR);
        }
    }
}
