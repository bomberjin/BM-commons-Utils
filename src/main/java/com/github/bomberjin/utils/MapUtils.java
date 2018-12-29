package com.github.bomberjin.utils;

import java.math.BigDecimal;

/**
 * @Author:bomber
 * @Date:Created in 下午7:12 2018/11/20
 * @Description: 地图相关工具
 * @Modified By:
 */
public class MapUtils {

    /**
     * 计算两个经纬度之间的距离  结果单位:米
     * @param latOne
     * @param lngOne
     * @param latTwo
     * @param lngTwo
     * @return
     */
    public static double  getDistance(BigDecimal latOne, BigDecimal lngOne, BigDecimal latTwo, BigDecimal lngTwo) {
        Double lat1 = latOne.doubleValue();
        Double lng1 = lngOne.doubleValue();
        Double lat2 = latTwo.doubleValue();
        Double lng2 = lngTwo.doubleValue();

        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }


    private static double EARTH_RADIUS = 6378137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }
}
