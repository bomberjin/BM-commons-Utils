package com.github.bomberjin.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyUtil {

    /**
     * 判断字符串是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9.]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判断字符串是否位带小数点 后两位
     * @param str
     * @return
     */
    public static boolean isNumberDotTwo(String str){
        Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    /**
     * Purpose:判断一个字符串是否为空
     *
     * @param value
     * @return
     */
    public static boolean isNotNull(String value) {
        boolean isNull = false;
        if (value != null && value.trim().length() > 0
                && !"null".equalsIgnoreCase(value)) {
            isNull = true;
        }
        return isNull;
    }

    /**
     * 判断传入对象是否位空
     * @param paramObject
     * @return
     */
    public static boolean isNullOrEmpty(Object paramObject) {
        return ((paramObject == null) || ("".equals(paramObject.toString())));
    }

    /**
     * 验证手机号码
     * @return
     */
    public static boolean checkMobileNumber(String mobileNumber){
        boolean flag = false;
        try{
            Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
            Matcher matcher = regex.matcher(mobileNumber);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }

}
