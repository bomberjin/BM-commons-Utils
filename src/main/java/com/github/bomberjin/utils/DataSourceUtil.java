package com.github.bomberjin.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.sql.*;

public class DataSourceUtil {

    /**
     * 动态链接数据库，并执行sql 查询语句，返回值可配合下面方法：resultSetToJson 转换为json
     * @param driver  数据库驱动
     *                mysql:com.mysql.jdbc.Driver
     *                oracle:oracle.jdbc.driver.OracleDriver
     * @param url    数据库地址
     *               mysql:jdbc:mysql://192.168.20.146:3306/dzfp_a9
     *               oracle:jdbc:oracle:thin:@192.168.20.5:1521:oracle11
     * @param user   数据库用户名
     * @param password    数据库密码
     * @param sql   执行sql（查询）
     * @return
     */
    public static ResultSet getQueryResult(String driver, String url, String user, String password, String sql){
        ResultSet result = null;

        Connection conn=null;
        Statement smt=null;
        try{
            Class.forName(driver).newInstance();
            conn= DriverManager.getConnection(url,user,password);
            if (conn != null) {
                System.out.println("数据库地址"+url+"连接成功！");

                smt = conn.createStatement();
                result=smt.executeQuery(sql);

            }else{
                System.out.println("数据库地址"+url+"连接失败！");
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库查询异常");
        }finally {
            try {
                //关闭数据库连接
                smt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }


    /**
     * 动态链接数据库，并执行sql 非查询语句
     * @param driver  数据库驱动
     *                mysql:com.mysql.jdbc.Driver
     *                oracle:oracle.jdbc.driver.OracleDriver
     * @param url    数据库地址
     *               mysql:jdbc:mysql://192.168.20.146:3306/dzfp_a9
     *               oracle:jdbc:oracle:thin:@192.168.20.5:1521:oracle11
     * @param user   数据库用户名
     * @param password    数据库密码
     * @param sql   执行sql（新增，修改，删除）
     * @return
     */
    public static boolean getUpdateResult(String driver,String url,String user,String password,String sql){
        boolean result = false;

        Connection conn=null;
        Statement smt=null;
        try{
            Class.forName(driver).newInstance();
            conn= DriverManager.getConnection(url,user,password);
            if (conn != null) {
                System.out.println("数据库地址"+url+"连接成功！");

                smt = conn.createStatement();
                result = smt.execute(sql);

            }else{
                System.out.println("数据库地址"+url+"连接失败！");
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库查询异常");
        }finally {
            try {
                //关闭数据库连接
                smt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * ResultSet 转成json 通常配合于getQueryResult 这个方法
     * @param rs
     * @return
     * @throws SQLException
     * @throws JSONException
     */
    public static String resultSetToJson(ResultSet rs) throws SQLException,JSONException {
        // json数组
        JSONArray array = new JSONArray();

        // 获取列数
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        // 遍历ResultSet中的每条数据
        while (rs.next()) {
            JSONObject jsonObj = new JSONObject();

            // 遍历每一列
            for (int i = 1; i <= columnCount; i++) {
                String columnName =metaData.getColumnLabel(i);
                String value = rs.getString(columnName);
                //此处将返回值中空值转换为 -  因为如果空值不设置值的情况下这组key value 不会加入到集合中
                if(VerifyUtil.isNullOrEmpty(value)){
                    value="-";
                }
                jsonObj.put(columnName, value);
            }
            array.add(jsonObj);
        }

        return array.toString();
    }
}
