package com.bubble.service;

import com.bubble.domain.entity.user.UserEntity;

import java.util.List;

/**
 * MySQL数据user表操作
 *
 * @author wugang
 * date: 2019-04-03 10:34
 **/
public interface UserService {

    UserEntity save(UserEntity user);

    List<UserEntity> findAll();

    List<UserEntity> delete(Integer id);

}
