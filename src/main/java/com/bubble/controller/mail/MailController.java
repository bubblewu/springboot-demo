package com.bubble.controller.mail;

import com.bubble.domain.ResultData;
import com.bubble.domain.entity.mail.SendMailEntity;
import com.bubble.service.CheckService;
import com.bubble.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

/**
 * 邮件发送
 *
 * @author wugang
 * date: 2019-04-03 19:03
 **/
@RestController
@RequestMapping("/mail")
@EnableAutoConfiguration
public class MailController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailController.class);

    private final MailService mailService;
    private final CheckService checkService;

    @Autowired
    public MailController(MailService mailService, CheckService checkService) {
        this.mailService = mailService;
        this.checkService = checkService;
    }

    /**
     * - POST:
     * http://localhost:8080/mail/send
     * - Body:
     * {
     * "title": "测试邮件",
     * "content": "来自SpringBoot-Demo的测试",
     * "targets":["111@qq.com","123@qq.com"]
     * }
     */
    @PostMapping(value = "/send")
    public ResultData send(@RequestBody @Valid SendMailEntity sendMail, Errors bodyErrors) {
        Instant begin = Instant.now();
        checkService.checkErrors(Optional.ofNullable(bodyErrors));
        ResultData resultData = new ResultData();
        boolean success = mailService.send(sendMail);
        if (success) {
            resultData.setCode(1);
            resultData.setMsg("Success");
            resultData.setData("");
        } else {
            resultData.setCode(0);
            resultData.setMsg("Failed");
            resultData.setData("");
        }

        LOGGER.info("send email to {} {}, costs {} ms", sendMail.getAllTargets(), success ? "success" : "failed", Duration.between(begin, Instant.now()).toMillis());
        return resultData;
    }

}
