package com.bubble.controller.login;

import com.bubble.dao.mysql.UserRepository;
import com.bubble.domain.entity.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

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
        String result = "登录成功";
        //根据用户名查询用户是否存在
        Optional<UserEntity> userOpt = userRepository.findOne((Specification<UserEntity>) (root, criteriaQuery, criteriaBuilder) -> {
            criteriaQuery.where(criteriaBuilder.equal(root.get("name"), user.getName()));
            return null;
        });

        if (userOpt.isPresent()) {
            UserEntity userEntity = userOpt.get();
            if (!userEntity.getPwd().equals(user.getPwd())) { // 密码错误
                result = "用户密码不相符，登录失败";
            } else {// 登录成功
                //将用户写入session
                request.getSession().setAttribute("_session_user", userEntity);
            }
        } else { // 用户不存在
            result = "用户不存在，登录失败";
        }
        return result;
    }
}
