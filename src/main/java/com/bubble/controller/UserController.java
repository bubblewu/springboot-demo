package com.bubble.controller;

import com.bubble.domain.entity.user.UserEntity;
import com.bubble.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * 基于Spring Data JPA的MySQL使用
 *
 * @author wugang
 * date: 2019-04-03 10:31
 **/
@RestController
@RequestMapping(value = "/user")
@EnableAutoConfiguration
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));/*TimeZone时区，解决差8小时的问题*/
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity save(UserEntity user) {
        return userService.save(user);
    }

    @GetMapping(value = "/find")
    public List<UserEntity> findAll() {
        System.out.println(userService.findAll().size());
        return userService.findAll();
    }

    @PutMapping(value = "/update")
    public int update(@RequestParam(value = "name") String name, String pwd) {
        return userService.updatePwdByName(name, pwd);
    }

    @DeleteMapping(value = "/delete")
    public int delete(@RequestParam(value = "name") String name) {
        return userService.delete(name);
    }


}
