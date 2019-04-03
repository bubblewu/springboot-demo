package com.bubble;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Profile;

/**
 * 通过Tomcat启动
 * 若要部署到外部servlet容器,需要继承SpringBootServletInitializer并重写configure()
 *
 * @author wugang
 * date: 2019-04-03 10:39
 **/
@Profile(value = "prod")
@SpringBootApplication
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        // 设置启动类,用于独立tomcat运行的入口
        return application.sources(SpringbootDemoApplication.class);
    }

}
