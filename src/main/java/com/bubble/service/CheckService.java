package com.bubble.service;

import org.springframework.validation.Errors;

import java.util.Optional;

/**
 * 接口异常检查
 *
 * @author wugang
 * date: 2019-04-04 11:01
 **/
public interface CheckService {

    void checkErrors(Optional<Errors> bodyErrors);

}
