package com.bubble.domain.entity.mail;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 邮件发送
 *
 * @author wugang
 * date: 2019-04-03 19:06
 **/
public class SendMailEntity implements Serializable {
    private static final long serialVersionUID = -66296236959741939L;

    @NotBlank(message = "邮件标题title不能为空")
    private String title;
    @NotBlank(message = "邮件内容content不能为空")
    private String content;
    @Size(min = 1, message = "收件人邮箱地址targets不能为空")
    private List<String> targets;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getTargets() {
        return targets;
    }

    public void setTargets(List<String> targets) {
        this.targets = targets;
    }

    public String getAllTargets() {
        if (null != targets) {
            StringBuilder sb = new StringBuilder();
            targets.forEach(t -> sb.append(t).append(","));
            return sb.substring(0, sb.length() - 1);
        }
        return "";
    }

}
