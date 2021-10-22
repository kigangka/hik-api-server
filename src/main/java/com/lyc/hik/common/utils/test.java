package com.lyc.hik.common.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args) {
        // 测试格式化ISO8601时间格式
        // 2018-07-26T15:00:00+08:00
        // 2021-09-07T17:48:49+08:00
        DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        DateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
        // 格式化时间
        String startTime = DateUtil.format(new Date(), sdf2);
        // 当前时间加一个月
        String endTime = DateUtil.format(DateUtil.offset(new Date(), DateField.MONTH, 1), sdf2);

        String end1Time = DateUtil.format(DateUtil.offset(new Date(), DateField.SECOND, -61), sdf2);

        String end2Time = DateUtil.format(DateUtil.offset(new Date(), DateField.YEAR, 2), sdf2);

        String end3Time = DateUtil.format(DateUtil.offset(new Date(), DateField.YEAR, 9), DatePattern.NORM_DATE_PATTERN);
        String end4Time = DateUtil.format(DateUtil.offset(new Date(), DateField.DAY_OF_YEAR, 15), DatePattern.NORM_DATE_PATTERN);

        /*System.out.println("开始时间：" + startTime);
        System.out.println("结束时间：" + end3Time);
        System.out.println("结束时间：" + end4Time);
*/
        String dateStr1 = "2017-03-01 22:33:23";
        Date date1 = DateUtil.parse(dateStr1);

        String dateStr2 = "2017-04-01 20:33:23";
        Date date2 = DateUtil.parse(dateStr2);
        long betweenDay = DateUtil.between(date1, date2, DateUnit.DAY);

        Date tmpValidity = DateUtil.offset(date1, DateField.MONTH, 6);
        System.out.println(tmpValidity);
        /*try {
            InetAddress addr = InetAddress.getLocalHost();
            System.out.println(addr.getHostName());
            System.out.println(addr.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }*/

        Integer aa = 3;

        Integer tmpSex = aa == 0 ? 1 : aa == 1 ? 2 : 0;
        System.out.println(tmpSex);
    }
}
