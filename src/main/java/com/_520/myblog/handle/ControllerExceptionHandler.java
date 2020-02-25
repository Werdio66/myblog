package com._520.myblog.handle;

import com._520.myblog.po.BaseResponse;
import com._520.myblog.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 *  处理异常
 */
@Slf4j
@ControllerAdvice("com._520.myblog.controller")
public class ControllerExceptionHandler {
    /**
     *  处理找不到资源异常
     */
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(Exception.class)
    public BaseResponse<?>  handlerNotFoundException(NotFoundException e){

//        BaseResponse<?> baseResponse = handlerBaseException(e);
//
//        baseResponse.setStatus(HttpStatus.NOT_FOUND.value());

//        baseResponse.setData(e.getErrorData());
        return null;
    }


    @ExceptionHandler(Exception.class)
    public String handlerException(HttpServletRequest request, Exception e) throws Exception {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
            throw e;
        }
        log.error("requestUrl = {}, errorMsg = {}", request.getRequestURL(), e);
        request.setAttribute("url", request.getRequestURL());
        request.setAttribute("exception", e);
        request.setAttribute("msg", e.getMessage());
        return "error/error";
    }

    private <T> BaseResponse<T> handlerBaseException(Throwable t){

        log.error("捕获异常：{}", t.getMessage());

        return null;
    }
}
