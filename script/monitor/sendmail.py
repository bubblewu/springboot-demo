#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @File  : sendmail.py
# @Author: wu gang
# @Date  : 2019/4/2
# @Desc  : 邮件发送
# @Contact: 752820344@qq.com

import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText

MAIL_SERVER = 'smtp.qiye.163.com'
MAIL_PORT = 465
MAIL_USER = 'userName'
MAIL_PASSWORD = 'password'


def sendmail(mailto, data, subject):
    msg = MIMEText(data, 'plain', 'utf-8')
    msg['subject'] = subject

    sender = 'monitor@111.cn'
    receivers = mailto.split(',')
    msg['From'] = sender
    msg['To'] = ','.join(receivers)

    smtpObj = smtplib.SMTP_SSL(MAIL_SERVER, MAIL_PORT)
    smtpObj.login(MAIL_USER, MAIL_PASSWORD)
    smtpObj.sendmail(sender, receivers, msg.as_string())
    smtpObj.quit()


def sendmail_with_attach(mailto, data, subject, filename):
    msg = MIMEMultipart()
    att = MIMEText(open(filename, 'rb').read(), 'base64', 'utf-8')
    att["Content-Type"] = 'application/octet-stream'
    att["Content-Disposition"] = 'attachment; filename="%s"' % (filename)
    msg.attach(att)

    body = MIMEText(data, 'plain', 'utf-8')
    msg.attach(body)

    msg['subject'] = subject

    sender = 'monitor@111.cn'
    receivers = mailto.split(',')
    msg['From'] = sender
    msg['To'] = ','.join(receivers)

    smtpObj = smtplib.SMTP_SSL(MAIL_SERVER, MAIL_PORT)
    smtpObj.login(MAIL_USER, MAIL_PASSWORD)
    smtpObj.sendmail(sender, receivers, msg.as_string())


def sendmail_with_attaches(mailto, data, subject, filename_list):
    msg = MIMEMultipart()

    for filename in filename_list:
        att = MIMEText(open(filename, 'rb').read(), 'base64', 'utf-8')
        att["Content-Type"] = 'application/octet-stream'
        att["Content-Disposition"] = 'attachment; filename="%s"' % filename
        msg.attach(att)

    body = MIMEText(data, 'plain', 'utf-8')
    msg.attach(body)

    msg['subject'] = subject

    sender = 'monitor@111.cn'
    receivers = mailto.split(',')
    msg['From'] = sender
    msg['To'] = ','.join(receivers)

    smtpObj = smtplib.SMTP_SSL(MAIL_SERVER, MAIL_PORT)
    smtpObj.login(MAIL_USER, MAIL_PASSWORD)
    smtpObj.sendmail(sender, receivers, msg.as_string())
    smtpObj.quit()
