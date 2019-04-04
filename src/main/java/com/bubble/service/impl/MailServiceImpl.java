package com.bubble.service.impl;

import com.bubble.domain.entity.mail.MailContentTypeEnum;
import com.bubble.domain.entity.mail.SendMailEntity;
import com.bubble.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 邮件服务实现
 *
 * @author wugang
 * date: 2019-04-03 19:10
 **/
@Service
public class MailServiceImpl implements MailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

    @Value(value = "${mail.smtp.service}")
    private String smtpService;
    @Value(value = "${mail.smtp.prot}")
    private String smtpPort;
    @Value(value = "${mail.from.address}")
    private String fromMailAddress;
    @Value(value = "${mail.from.smtp.pwd}")
    private String fromMailStmpPwd;
    @Value(value = "${mail.from.nickname}")
    private String fromMailNickname;

    private Properties props;

    @PostConstruct
    public void init() {
        // 记录邮箱的一些属性
        props = new Properties();
        // 表示SMTP发送邮件，必须进行身份验证
        props.put("mail.smtp.auth", "true");
        // SMTP服务器
        props.put("mail.smtp.host", smtpService);
        //设置端口号，QQ邮箱给出了两个端口465/587
        props.put("mail.smtp.port", smtpPort);
        // 发送邮箱
        props.put("mail.user", fromMailAddress);
        // 发送邮箱的16位STMP口令
        props.put("mail.password", fromMailStmpPwd);
    }

    @Override
    public boolean send(SendMailEntity sendMailEntity) {
        AtomicBoolean success = new AtomicBoolean(true);
        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromMailAddress, fromMailStmpPwd);
            }
        };
        try {
            // 使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);
            // 创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            // 设置发件人
            String nickName = MimeUtility.encodeText(fromMailNickname);
            InternetAddress form = new InternetAddress(nickName + " <" + fromMailAddress + ">");
            message.setFrom(form);

            // 设置邮件标题
            message.setSubject(sendMailEntity.getTitle());
            // html发送邮件, 设置邮件的内容体
            message.setContent(sendMailEntity.getContent(), MailContentTypeEnum.HTML.getValue());
            //文本发送邮件
//            message.setContent(sendMailEntity.getContent(), MailContentTypeEnum.TEXT.getValue());
            // 发送邮箱地址
            sendMailEntity.getTargets().forEach(target -> {
                try {
                    // 设置收件人的邮箱
                    InternetAddress to = new InternetAddress(target);
                    message.setRecipient(Message.RecipientType.TO, to);
                    Transport.send(message);
                } catch (Exception e) {
                    LOGGER.error("send mail to {} error.", target, e);
                    success.set(false);
                }

            });
        } catch (Exception e) {
            LOGGER.error("send mail error.", e);
            success.set(false);
        }
        return success.get();
    }

}
