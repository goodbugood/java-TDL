package shali.tdl.jdk.util;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DateTest {
    /**
     * 时间戳 1721615718 -> 2024-07-22 10:35:18
     */
    private final long TIMESTAMP = 1721615718;

    private final String DATE = "2024-07-22";

    private final String DATETIME = "2024-07-22 10:35:18";

    private final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Test
    public void timestamp() {
        // 获取当前时间戳
        // 13 位的 long
        long time1 = System.currentTimeMillis();
        Long longTime1 = new Long(time1);
        assertEquals(13, longTime1.toString().length());
        // 方法二
        long time2 = Calendar.getInstance().getTimeInMillis();
        Long longTime2 = Long.valueOf(time2);
        assertEquals(13, longTime2.toString().length());
        // 方法三
        long time3 = new Date().getTime();
        Long longTime3 = Long.valueOf(time3);
        assertEquals(13, longTime3.toString().length());
        // 10 位，兼容 php
        Long time4 = Long.valueOf(longTime1 / 1000);
        assertEquals(10, time4.toString().length());
        // 方法二
        Long longTime4 = Long.valueOf(Calendar.getInstance().getTimeInMillis() / 1000);
        assertEquals(10, longTime4.toString().length());
        // 方法三
        Long longTime5 = Long.valueOf(new Date().getTime() / 1000);
        assertEquals(10, longTime5.toString().length());
    }

    /**
     * 获取当前日期
     */
    @Test
    public void date() {
        // 指定时区的时间格式
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT, Locale.CHINA);
        // java.util.Date 已经过时，不建议使用
        Date date = new Date(TIMESTAMP);
        Instant instant = date.toInstant();
        System.out.println(date.toString());
        LocalDate localDate = LocalDate.parse(DATETIME, dateTimeFormatter);
        assertEquals("2024-07-22", localDate.toString());
        // 获取年月日
        assertEquals(2024, localDate.getYear());
        assertEquals(7, localDate.getMonthValue());
        assertEquals(22, localDate.getDayOfMonth());
    }

    /**
     * 练习 LocalDateTime
     */
    @Test
    public void localDateTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
        LocalDateTime localDateTime = LocalDateTime.parse(DATETIME, dateTimeFormatter);
        // 获取年月日时分秒，直接转换为字符串是不可以的
        assertEquals("2024-07-22T10:35:18", localDateTime.toString());
        assertEquals(DATETIME, localDateTime.format(dateTimeFormatter));
        // 获取年月日时分秒
        assertEquals(2024, localDateTime.getYear());
        assertEquals(7, localDateTime.getMonthValue());
        assertEquals(22, localDateTime.getDayOfMonth());
        assertEquals(10, localDateTime.getHour());
        assertEquals(35, localDateTime.getMinute());
        assertEquals(18, localDateTime.getSecond());
        assertEquals(0, localDateTime.getNano());
        // 修改时间月份
        assertEquals("2024-12-22 10:35:18", localDateTime.withMonth(12).format(dateTimeFormatter));
        assertEquals("2024-07-22 21:35:18", localDateTime.withHour(21).format(dateTimeFormatter));
        // 转换为时间戳
        Instant instant = localDateTime.toInstant(ZoneOffset.of("+08:00"));
        // 时间戳，单位秒
        assertEquals(TIMESTAMP, instant.getEpochSecond());
        // 13 位时间戳，单位毫秒
        assertEquals(TIMESTAMP * 1000, instant.toEpochMilli());
    }

    /**
     * 练习 LocalDate
     */
    @Test
    public void localDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(DATE, dateTimeFormatter);
        // 获取格式化的年月日
        assertEquals("2024-07-22", localDate.toString());
        assertEquals(DATE, localDate.format(dateTimeFormatter));
        // 获取年月日
        assertEquals(2024, localDate.getYear());
        assertEquals(7, localDate.getMonthValue());
        assertEquals(22, localDate.getDayOfMonth());
        // 获取一年有多少天
        assertEquals(366, localDate.lengthOfYear());
        // 获取一个月有多少天
        assertEquals(31, localDate.lengthOfMonth());
        // 是否是闰年
        assertTrue(localDate.isLeapYear());
    }
}
