package com.bubble.domain.entity.mail;

/**
 * 邮件格式
 *
 * @author wugang
 * date: 2019-04-02 19:11
 **/
public enum MailContentTypeEnum {
    HTML("text/html;charset=UTF-8"), //html格式
    TEXT("text");
    private String value;

    MailContentTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
