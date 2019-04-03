package com.bubble.service;

import com.bubble.dao.mysql.UserRepository;
import com.bubble.domain.entity.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MySQL数据user表操作
 *
 * @author wugang
 * date: 2019-04-03 10:33
 **/
//@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity save(UserEntity user) {
//        return userRepository.save();
        return null;
    }

    @Override
    public List<UserEntity> findAll() {
//        return userRepository.findAll();
        return null;
    }

    @Override
    public List<UserEntity> delete(Integer id) {
//        userRepository.delete(id);
//        return userRepository.findAll();
        return null;
    }
}
