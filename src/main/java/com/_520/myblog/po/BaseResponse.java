package com._520.myblog.po;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 *  响应实体    （默认是 200）
 * @param <T>   响应的类型
 */
@Data
public class BaseResponse<T> {

    // 状态
    private Integer status;

    // 响应的消息
    private String message;

    // 响应数据
    private T data;

    // 响应代码格式
    private String devMessage;

    public BaseResponse(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    /**
     *  自定义返回信息和返回数据
     * @param message   返回信息
     * @param data      返回数据
     * @param <T>       自定义类型
     * @return          响应实体
     */
    public static <T> BaseResponse<T> ok(String message, T data){
        return new BaseResponse<>(HttpStatus.OK.value(), message, data);
    }

    /**
     *  自定义返回消息
     * @param message   返回消息
     * @param <T>       自定义类型
     * @return          响应实体
     */
    public static <T> BaseResponse<T> ok(String message){
        return ok(message, null);
    }

    /**
     *  传入响应的数据
     * @param data      响应数据
     * @param <T>       类型
     * @return          响应实体
     */
    public static <T> BaseResponse<T> ok(T data){
        return new BaseResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data);
    }
}
