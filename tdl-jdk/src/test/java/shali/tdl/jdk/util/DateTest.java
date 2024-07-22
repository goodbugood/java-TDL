package shali.tdl.jdk.util;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class DateTest {
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
        // 时间戳 1721618978 -> 2024-07-22 10:35:18
        long timestamp = 1721618978;
        String strDateTime = "2024-07-22 10:35:18";
        // 指定时区的时间格式
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        // java.util.Date 已经过时，不建议使用
        Date date = new Date(timestamp);
        Instant instant = date.toInstant();
        LocalDate localDate = LocalDate.parse(strDateTime, dateTimeFormatter);
        assertEquals("2024-07-22", localDate.toString());
        // 获取年月日
        assertEquals(2024, localDate.getYear());
        assertEquals(7, localDate.getMonthValue());
        assertEquals(22, localDate.getDayOfMonth());
    }
}
