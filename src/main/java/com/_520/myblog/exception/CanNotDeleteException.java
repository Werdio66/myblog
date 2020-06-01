package com._520.myblog.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Werdio丶
 * @since 2020-06-01 11:45:15
 */
public class CanNotDeleteException extends MyBlogException {

    public CanNotDeleteException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return null;
    }
}
