package com._520.myblog.exception;

import org.springframework.http.HttpStatus;

/**
 *  异常类
 */
public abstract class MyBlogException extends RuntimeException {

    // 存放错误信息
    private Object errorData;

    public MyBlogException(String message) {
        super(message);
    }

    public MyBlogException(String message, Throwable cause) {
        super(message, cause);
    }


    public Object getErrorData() {
        return errorData;
    }

    /**
     *  返回状态    200,404,403等
     */
    public abstract HttpStatus getStatus();
    /**
     *  注入错误信息，并返回异常对象
     * @param errorData     错误信息
     * @return              当前异常对象
     */
    public MyBlogException setErrorData(Object errorData) {
        this.errorData = errorData;
        return this;
    }
}
