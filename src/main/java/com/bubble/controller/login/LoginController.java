package com.bubble.controller.login;

import com.bubble.dao.mysql.UserRepository;
import com.bubble.domain.entity.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

/**
 * 登录
 *
 * @author wugang
 * date: 2019-04-03 18:02
 **/
@RestController
@RequestMapping(value = "/user")
@EnableAutoConfiguration
public class LoginController {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/login")
    public String login(UserEntity user, HttpServletRequest request) {
        //登录成功
        boolean flag = true;
        String result = "登录成功";
        //根据用户名查询用户是否存在
        UserEntity userEntity = userRepository.findOne(new Specification<UserEntity>() {
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("name"), user.getName()));
                return null;
            }
        }).orElse(null);
        //用户不存在
        if (userEntity == null) {
            flag = false;
            result = "用户不存在，登录失败";
        }
        //密码错误
        else if (!userEntity.getPwd().equals(user.getPwd())) {
            flag = false;
            result = "用户密码不相符，登录失败";
        }
        //登录成功
        if (flag) {
            //将用户写入session
            request.getSession().setAttribute("_session_user", userEntity);
        }
        return result;
    }
}
