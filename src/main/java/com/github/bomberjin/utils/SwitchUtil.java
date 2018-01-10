package com.github.bomberjin.utils;

public class SwitchUtil {

    /**
     * 把传入的数转换为中文金额大写形式
     *
     * @param flag
     *            int 标志位，1 表示转换整数部分，0 表示转换小数部分
     * @param s
     *            String 要转换的字符串
     * @return 转换好的带单位的中文金额大写形式
     */
    private String numFormat(int flag, String s) {
        int sLength = s.length();
        // 货币大写形式
        String bigLetter[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
        // 货币单位
        String unit[] = { "元", "拾", "佰", "仟", "万",
                // 拾万位到仟万位
                "拾", "佰", "仟",
                // 亿位到万亿位
                "亿", "拾", "佰", "仟", "万" };
        String small[] = { "分", "角" };
        // 用来存放转换后的新字符串
        String newS = "";
        // 逐位替换为中文大写形式
        for (int i = 0; i < sLength; i++) {
            if (flag == 1) {
                // 转换整数部分为中文大写形式（带单位）
                newS = newS + bigLetter[s.charAt(i) - 48]
                        + unit[sLength - i - 1];
            } else if (flag == 2) {
                // 转换小数部分（带单位）
                newS = newS + bigLetter[s.charAt(i) - 48]
                        + small[sLength - i - 1];
            }
        }
        return newS;
    }

    /**
     * 把null转换成空字符串
     */
    public static String NulltoString(Object o) {
        if (o == null)
            return "";
        return o.toString();
    }
    public static String NulltoZero(Object o) {
        if (o == null)
            return "0";
        if(o.equals(""))
            return "0";
        return o.toString();
    }
}
