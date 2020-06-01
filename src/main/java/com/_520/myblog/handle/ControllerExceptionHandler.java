package com._520.myblog.handle;

import com._520.myblog.po.BaseResponse;
import com._520.myblog.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 *  处理异常
 */
@Slf4j
@ControllerAdvice("com._520.myblog.controller")
public class ControllerExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ModelAndView handlerException(HttpServletRequest request, Exception e) {

        log.error("requestUrl = {}, errorMsg = {}", request.getRequestURL(), e);
        request.setAttribute("url", request.getRequestURL());
        request.setAttribute("exception", e);
        request.setAttribute("msg", e.getMessage());

        return new ModelAndView("/error/error");
    }

}
