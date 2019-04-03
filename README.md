# springboot-demo
记录平时用到的一些SpringBoot的小Demo
# 主要功能
## 脚本类
- 脚本自动打包部署（Jar瘦身，项目与依赖分离）；
- 服务器任务启动和关闭脚本；
- 日志异常监控，并邮件报警；
- Spark任务集群部署脚本

## 功能类
- Log4j2日志系统配置；
- 对POST参数进行验证（自定义日期格式验证）；
- 异常信息拦截，输出指定数据格式；
- Web登录页功能；

### DB相关
- JetCache二级缓存：本地Caffeine + 远程Redis（含缓存命中信息等日志配置）；
（可参考：[JetCache官方文档](https://github.com/alibaba/jetcache/wiki)）
- MongoDB数据源操作：单源和多源；
- Spring Data JPA 操作MySQL（[JPA和Spring Data](http://blog.didispace.com/books/spring-boot-reference/IV.%20Spring%20Boot%20features/29.3.%20JPA%20and%20%E2%80%98Spring%20Data%E2%80%99.html)）；
- JDBC连接池、监控组件Druid配置 [Druid](https://github.com/alibaba/druid])；

