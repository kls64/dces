package com.hust.dces.Utils;

import java.util.Arrays;
import java.util.Random;

public final class EmailRegisterValidator {

    static private byte[] verificationCode = null;

    public EmailRegisterValidator(int digits) {
        byte[] verificationCode = new byte[digits];
    }

    // 随机生成 digits 个数字验证码
    private static void generateVerificationCode() {
        long timestamp = System.currentTimeMillis();
        Random random = new Random(timestamp);
        for (int i = 0; i < verificationCode.length; ++i) {
            verificationCode[i] = (byte) random.nextInt(10);
            random.setSeed(random.nextInt());
        }
    }

    // 将随机生成的验证码发送到指定邮箱
    private static void sendVerificationCodeToEmail(String email) {

    }

    public static void main(String args[]) {
        System.out.println("this is main");
        EmailRegisterValidator erv = new EmailRegisterValidator(6);
        for (int i = 0; i < 1000; ++i) {
            erv.generateVerificationCode();
            System.out.println(Arrays.toString(verificationCode));
        }
    }

}
