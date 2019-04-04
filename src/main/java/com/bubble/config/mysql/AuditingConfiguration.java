package com.bubble.config.mysql;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author wugang
 * date: 2019-04-03 10:42
 **/
@EnableAsync
@SpringBootApplication
@EnableJpaAuditing
public class AuditingConfiguration {
}
