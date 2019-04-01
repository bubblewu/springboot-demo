package com.bubble.domain.exception;

import java.io.Serializable;

/**
 * 参数验证异常
 *
 * @author wugang
 * date: 2019-04-01 17:54
 **/
public class ValidationFailedException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -4072744353312738304L;

    public ValidationFailedException() {
    }

    public ValidationFailedException(String s) {
        super(s);
    }

}
