package com.hust.dces.Utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class EmailRegisterValidator {

    // 保存邮箱验证码
    private byte[] verificationCode = null;
    private String vcString = null;
    // 设置验证码过期时间
    private static long expireTime; // 60s
    private long vcTime; // 生成验证码的时间戳

    // 构造函数
    public EmailRegisterValidator(int digits, long expireSeconds) {
        verificationCode = new byte[digits];
        expireTime = expireSeconds * 1000;
    }

    // 随机生成 digits 个数字验证码
    private void generateVerificationCode() {
        long timestamp = System.currentTimeMillis();
        Random random = new Random(timestamp);
        for (int i = 0; i < verificationCode.length; ++i) {
            verificationCode[i] = (byte) random.nextInt(10);
            random.setSeed(random.nextInt());
        }
    }

    // 判断邮箱格式是否合法
    private boolean isValidEmail(String email) {
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(email);
        if (m.matches())
            return true;
        else
            return false;
    }

    // 将随机生成的验证码发送到指定邮箱
    public void sendVerificationCodeToEmail(String email) {
        if (null != email && isValidEmail(email)) {
            // 生成验证码
            generateVerificationCode();
            String vc = String.valueOf(verificationCode[0]);
            int len = verificationCode.length;
            for (int i = 1; i < len; ++i) {
                vc += String.valueOf(verificationCode[i]);
            }
            vcString = vc;

            // 发送验证码到邮箱
            // 收件人电子邮箱
            String to = email;

            // 发件人电子邮箱
            String from = "lovetsw@foxmail.com";

            // 指定发送邮件的主机为 localhost
            String host = "smtp.qq.com";

            // 获取系统属性
            Properties properties = System.getProperties();

            // 设置邮件服务器
            properties.setProperty("mail.smtp.host", host);
            properties.put("mail.smtp.auth", "true");


            // 获取默认session对象
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("lovetsw@foxmail.com", "kyfyosmvtuevdige"); //发件人邮件用户名、授权码
                }
            });

            try {
                // 创建默认的 MimeMessage 对象
                MimeMessage message = new MimeMessage(session);

                // Set From: 头部头字段
                message.setFrom(new InternetAddress(from));

                // Set To: 头部头字段
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                // Set Subject: 头部头字段
                message.setSubject("基于Spark的文档内容提取系统验证码");

                // 设置消息体
                message.setText("验证码: " + vcString + "\n" + "有效时间为 " + String.valueOf(expireTime / 1000) + "s.");

                // 发送消息
                Transport.send(message);

                System.out.println("Sent message successfully....");

                // 设置验证码开始时间
                vcTime = System.currentTimeMillis();
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("非法的邮箱参数！");
        }
    }

    // 检查验证码是否正确并且是否过期
    public boolean isCorrectAndNotExpired(String userVC) {
        long nowTimeStamp = System.currentTimeMillis();
        if (userVC.equals(vcString) && nowTimeStamp <= (vcTime + expireTime)) {
            return true;
        } else {
            return false;
        }
    }


//    public static void main(String args[]) {
//        System.out.println("this is main.");
//        EmailRegisterValidator erv = new EmailRegisterValidator(6, 60);
///*        for (int i = 0; i < 1000; ++i) {
//            erv.generateVerificationCode();
//            System.out.println(Arrays.toString(verificationCode));
//        }*/
//        erv.sendVerificationCodeToEmail("1370905044@qq.com");
//
//    }

}
