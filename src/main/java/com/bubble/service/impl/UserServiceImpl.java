package com.bubble.service.impl;

import com.bubble.dao.mysql.UserRepository;
import com.bubble.domain.entity.user.UserEntity;
import com.bubble.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MySQL数据user表操作
 *
 * @author wugang
 * date: 2019-04-03 10:33
 **/
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public int updatePwdByName(String name, String pwd) {
        return userRepository.updatePwdByName(name, pwd);
    }

    @Override
    public int delete(String name) {
        return userRepository.deleteByName(name);
    }
}
