package com.zrkworld.cinema.utils;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author zrk
 * @version 1.0
 * @date 2020/5/1 0001 11:45
 */
public class DateManageSystem {
    /**
     * 返回上一周的周一到周日的字符串格式日期（时分秒清零）
     * @return String[]
     * @throws ParseException
     */
    public String[] getLastWeek()  {
        Calendar cal = Calendar.getInstance();
        String[] week = new String[7];
        //n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
        int n = -1;
        cal.add(Calendar.DATE, n * 7);
        //想周几，这里就传几Calendar.MONDAY（TUESDAY...）
        for (int i = 0; i < 7; i++) {
            cal.set(Calendar.DAY_OF_WEEK, 2 + i);
            //时分秒毫秒域清零
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            week[i] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
        }
        return week;
    }
/*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
Calendar c = Calendar.getInstance();
1.过去七天
c.setTime(new Date());
c.add(Calendar.DATE, - 7);
Date d = c.getTime();
String day = format.format(d);
System.out.println("过去七天："+day);
2.过去一月
c.setTime(new Date());
c.add(Calendar.MONTH, -1);
Date m = c.getTime();
String mon = format.format(m);
System.out.println("过去一个月："+mon);
3.过去三个月
c.setTime(new Date());
c.add(Calendar.MONTH, -3);
Date m3 = c.getTime();
String mon3 = format.format(m3);
System.out.println("过去三个月："+mon3);
4.过去一年
c.setTime(new Date());
c.add(Calendar.YEAR, -1);
Date y = c.getTime();
String year = format.format(y);
System.out.println("过去一年："+year);
5.现在时间24小时制
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
String nowDate=format.format(new Date());
System.out.println(nowDate);
6.当天的开始时间
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
Calendar c = Calendar.getInstance();
String start = format.format(c.getTime())+" 00:00:00";
System.out.println(start);
7.当天的截止时间
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
Calendar calendar = Calendar.getInstance();
String end = format.format(calendar.getTime())+" 23:59:59";
System.out.println(end);
8.当前时间的前一个星期 开始时间
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
Calendar c = Calendar.getInstance();
c.add(Calendar.DAY_OF_MONTH, -6);
String start = format.format(c.getTime())+" 00:00:00";
System.out.println(start);
9.当天日期前一个月
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
Calendar c = Calendar.getInstance();
c.add(Calendar.MONTH, -1);    //得到前一个月
String start = format.format(c.getTime())+" 00:00:00";
System.out.println(start);
10.当前时间的前一年开始时间
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
Calendar c = Calendar.getInstance();
c.add(Calendar.YEAR, -1); //年份减1
String start =format.format(c.getTime())+" 00:00:00";
System.out.println(start);
11.当前时间的周一时间和周末时间 setfirstdayofweek（）方法
SimpleDateFormat format  = new SimpleDateFormat("YYYY-MM-dd ");
Calendar c = Calendar.getInstance();
c.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
String weekStart = format.format(c.getTime())+" 00:00:00";
System.out.println(weekStart);
Calendar ca = Calendar.getInstance();
ca.setFirstDayOfWeek(Calendar.MONDAY);
ca.set(Calendar.DAY_OF_WEEK, ca.getFirstDayOfWeek() + 6); // Sunday
String weekEnd = format.format(ca.getTime())+" 23:59:59";
System.out.println(weekEnd);
12.当前时所在月份的第一天和最后一天
SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
Calendar c = Calendar.getInstance();
c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
String monthStart = format.format(c.getTime())+" 00:00:00";
System.out.println(monthStart);
Calendar ca = Calendar.getInstance();
ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
String monthEnd = format.format(ca.getTime())+" 23:59:59";
System.out.println(monthEnd);
13.当期时间所在年份的开始时间
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
Calendar c = Calendar.getInstance();
c.set(c.get(Calendar.YEAR) ,0, 1);//开始时间日期
String yearStart = format.format(c.getTime())+" 00:00:00";
System.out.println(yearStart);
Calendar ca = Calendar.getInstance();
ca.set(ca.get(Calendar.YEAR) ,11, ca.getActualMaximum(Calendar.DAY_OF_MONTH));//结束日期
String yearEnd = format.format(ca.getTime())+" 23:59:59";
System.out.println(yearEnd);
14.计算时间差取得两个时间的微秒级的时间差
//得微秒级时间差
long val = calendarEnd.getTimeInMillis() - calendarBegin.getTimeInMillis();
//换算后得到天数
long day = val / (1000 * 60 * 60 * 24);
15.获取某个时间的上周一和周日
Calendar cal = Calendar.getInstance();
//n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
int n = 1;
String monday;
cal.add(Calendar.DATE, n*7);
//想周几，这里就传几Calendar.MONDAY（TUESDAY...）
cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
monday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
System.out.println(monday);*/
}
