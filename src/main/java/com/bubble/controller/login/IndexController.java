package com.bubble.controller.login;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录页面
 *
 * @author wugang
 * date: 2019-04-03 17:38
 **/
@Controller
@RequestMapping("/user")
@EnableAutoConfiguration
public class IndexController {

    /**
     * 初始化登录页面
     *
     * @return "login"
     */
    @GetMapping(value = "/login_view")
    public String loginView() {
        return "login";
    }

    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }

}
