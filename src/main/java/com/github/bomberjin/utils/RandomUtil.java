package com.github.bomberjin.utils;

import java.util.Random;
import java.util.UUID;

public class RandomUtil {
    private static final Random random = new Random();

    private static char[] chars = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u',
            'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U',
            'V', 'W', 'X', 'Y', 'Z'
    };

    /**
     * 获取UUID
     * 去除-
     * @return
     */
    public static String getUUID() {
        String result ;
        result = UUID.randomUUID().toString() + UUID.randomUUID().toString();
        result = result.replaceAll("-", "");
        return result;
    }

    /**
     * 获取多次uuid
     * 去除-
     * @param time
     * @return
     */
    public static String getUUID(int time){
        String result ="";
        for(int i=1;i<=time;i++){
            result +=UUID.randomUUID().toString();
        }

        result = result.replaceAll("-", "");
        return result;
    }


    /**
     * 随机生成由0-9a-zA-Z组合而成的字符串
     *
     * @param len 字符串长度
     * @return 生成结果
     */
    public static String randomChar(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(chars[random.nextInt(chars.length)]);
        }
        return sb.toString();
    }


    /**
     * 生成制定长度验证码
     *
     * @param verifyCode_len
     *            验证码长度
     * @return String
     */
    private synchronized static String getRandmonVerifyCode(int verifyCode_len) {
        char[] c = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        int maxNum = 100;
        int count = 0;// 记录验证码长度

        StringBuffer verifyCodeStr = new StringBuffer();
        Random random = new Random(System.currentTimeMillis());
        while (count < verifyCode_len) {
            int i = random.nextInt(maxNum);
            if (i >= 0 && i < c.length) {
                verifyCodeStr.append(String.valueOf(i));
                count++;
            }
        }
        return verifyCodeStr.toString();
    }
}
