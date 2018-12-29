package com.github.bomberjin.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyUtil {
    /**
     * 6位数字 验证码正则
     */
    public static final String REGEX_MOBILE_VERIFY_CODE = "^\\d{6}\\b";


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
            Pattern regex = Pattern.compile("^(((13[0-9])|(19[0-9])|(16[0-9])|(17[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
            Matcher matcher = regex.matcher(mobileNumber);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }

    /**
     * 18位身份证校验,比较严格校验
     * @author lyl
     * @param idCard
     * @return
     */
    public static boolean is18ByteIdCardComplex(String idCard){
        Map<String, String> cityMap;
        cityMap = new HashMap<>();
        cityMap.put("11", "北京");
        cityMap.put("12", "天津");
        cityMap.put("13", "河北");
        cityMap.put("14", "山西");
        cityMap.put("15", "内蒙古");

        cityMap.put("21", "辽宁");
        cityMap.put("22", "吉林");
        cityMap.put("23", "黑龙江");

        cityMap.put("31", "上海");
        cityMap.put("32", "江苏");
        cityMap.put("33", "浙江");
        cityMap.put("34", "安徽");
        cityMap.put("35", "福建");
        cityMap.put("36", "江西");
        cityMap.put("37", "山东");

        cityMap.put("41", "河南");
        cityMap.put("42", "湖北");
        cityMap.put("43", "湖南");
        cityMap.put("44", "广东");
        cityMap.put("45", "广西");
        cityMap.put("46", "海南");

        cityMap.put("50", "重庆");
        cityMap.put("51", "四川");
        cityMap.put("52", "贵州");
        cityMap.put("53", "云南");
        cityMap.put("54", "西藏");

        cityMap.put("61", "陕西");
        cityMap.put("62", "甘肃");
        cityMap.put("63", "青海");
        cityMap.put("64", "宁夏");
        cityMap.put("65", "新疆");

        cityMap.put("71", "台湾");
        cityMap.put("81", "香港");
        cityMap.put("82", "澳门");
        cityMap.put("91", "国外");

        int[] prefix = new int[]{7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};
        char[] suffix = new char[]{ '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };
        Pattern pattern = Pattern.compile("^(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)?$");

        if(pattern.matcher(idCard).matches()){
            if(cityMap.get(idCard.subSequence(0,2).toString()) == null ){
                return false;
            }
            int idCardWiSum=0;  //用来保存前17位各自乖以加权因子后的总和
            for(int i=0;i<17;i++){
                idCardWiSum+=(idCard.charAt(i) - '0') *prefix[i];
            }

            int idCardMod=idCardWiSum%11;//计算出校验码所在数组的位置
            char idCardLast=idCard.charAt(17);//得到最后一位身份证号码

            return idCardLast == suffix[idCardMod];
        }
        return false;
    }

    /**
     * 是否为验证码(6位数字)
     * @param verifyCode
     * @return
     */
    public static boolean isMobileVerification(String verifyCode){

        return verifyCode != null && verifyCode.length() > 0 && Pattern.matches(REGEX_MOBILE_VERIFY_CODE, verifyCode);
    }


}
