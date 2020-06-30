package com.zz.my.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 自定义业务异常类
 * ClassName: NotFoundException
 * Description: @ResponseStatus定义异常状态码
 * date: 2020/6/22 19:40
 *
 * @author ZZ
 * @version 1.0.0
 * @since JDK 1.8
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
