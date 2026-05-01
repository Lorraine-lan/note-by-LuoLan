package com.hgsf.supermarket.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: wanjianhong
 * @Version: 1.0
 * @Description:
 *
 * 日期工具类
 *
 */
public class DateUtil {

    /**
     * @description: 日期时间类型转换字符串
     * @param: [date 日期时间]
     * @return: java.lang.String 字符串
     **/
    public static String dateToString(Date date){
        //创建处理日期的类SimpleDateFormat对象
        SimpleDateFormat sdf = new SimpleDateFormat();  //使用默认方式对日期进行格式化处理
        //调用格式化处理的方法formate
        return sdf.format(date);
    }

    /**
     * @description: 根据格式化要求来将日期时间类型转换字符串
     * @param: [date, pattern]
     * @return: java.lang.String
     **/
    public static String dateToString(Date date, String pattern){
        //创建处理日期的类SimpleDateFormat对象
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);  //使用默认方式对日期进行格式化处理
        //调用格式化处理的方法formate
        return sdf.format(date);
    }

    /**
     * @description: 字符串日期类型转换为Date类型
     * @param: [dateStr, pattern]
     * @return: java.util.Date
     **/
    public static Date stringToDate(String dateStr, String pattern){
        //1.创建SimpleDateFormat对象
        SimpleDateFormat sdf  =new SimpleDateFormat(pattern);  //格式化时也必须时 - 符号,要 和被转换的日期字符串格式一致
        //2.调用方法将字符串转换为date类型
        try {
            Date date = sdf.parse(dateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
