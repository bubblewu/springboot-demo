package com.bubble.service;

import com.bubble.domain.entity.mail.SendMailEntity;

/**
 * 邮件服务
 *
 * @author wugang
 * date: 2019-04-03 19:10
 **/
public interface MailService {

    boolean send(SendMailEntity sendMailEntity);

}
