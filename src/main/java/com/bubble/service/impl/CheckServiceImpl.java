package com.bubble.service.impl;

import com.bubble.domain.exception.ValidationFailedException;
import com.bubble.service.CheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.Optional;

/**
 * 接口异常检查
 *
 * @author wugang
 * date: 2019-04-04 11:02
 **/
@Service
public class CheckServiceImpl implements CheckService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckServiceImpl.class);

    @Override
    public void checkErrors(Optional<Errors> bodyErrors) {
        StringBuilder sb = new StringBuilder();
        boolean isError = false;
        if (bodyErrors.isPresent() && bodyErrors.get().hasErrors()) {
            isError = true;
            bodyErrors.get().getAllErrors().forEach(error -> {
                if (error instanceof FieldError) {
                    FieldError fieldError = (FieldError) error;
                    sb.append("Body参数: [").append(fieldError.getField());
                    sb.append("] ").append(fieldError.getDefaultMessage());
                } else {
                    sb.append(error.getDefaultMessage());
                }
                sb.append(", ");
            });

        }
        if (isError) {
            String msg = sb.substring(0, sb.length() - 2);
            LOGGER.error("valid params illegal: {}", msg);
            throw new ValidationFailedException(msg);
        }
    }

}
