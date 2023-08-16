package shali.tdl.hutool.core;

import cn.hutool.core.date.*;

import static org.junit.Assert.*;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public class DateUtilTest {
    /**
     * 他文档里说,第三个参数用来控制是否计算首尾的
     * hutool 获取两个日期天数,有 bug,他使用的是 floor,不是 ceil
     * 2023-06-01 00:00:00 - 2023-06-01 10:00:00,算 1 天,有 1 天,算 1 天
     * 2023-06-01 00:00:00 - 2023-06-02 00:00:02,算 1 天,按秒算,取余
     * 2023-06-01 00:00:00 - 2023-06-02 00:00:02,算 2 天,按秒算,进 1
     */
    @Test
    public void betweenDay() {
        String startDate = "2023-06-01 00:00:00";
        String endDate = "2023-06-02 23:59:59";
        DateTime startDateTime = DateUtil.parse(startDate);
        DateTime endDateTime = DateUtil.parse(endDate);
        assertEquals(1L, DateUtil.betweenDay(startDateTime, endDateTime, false));
        assertEquals(1L, DateUtil.betweenDay(startDateTime, endDateTime, true));
        // hutool 源码计算天数,就是间隔毫秒数 / 单位毫秒数,求余数
        assertEquals(1L, (DateUnit.DAY.getMillis() + 1) / DateUnit.DAY.getMillis());
    }

    @Test
    public void formatTime() {
        DateTime date = DateUtil.date();
        Date date1 = new Date();
        //
        String now = DateUtil.now();
        // 获取当前时间对象
        String now2 = date.toString();
        // 格式化
        String now3 = DateUtil.format(date1, DatePattern.NORM_DATETIME_PATTERN);
        // assertEquals(now, now3);
        // 获取日期时间
        String now4 = DateUtil.formatDateTime(date1);
        assertEquals(now, now4);
        // 获取日期
        String day = DateUtil.format(date, DatePattern.NORM_DATE_PATTERN);
        String day1 = DateUtil.format(date1, DatePattern.NORM_DATE_PATTERN);
        String day2 = date.toString(DatePattern.NORM_DATE_PATTERN);
        assertEquals(day, day1);
        assertEquals(day1, day2);
        // 获取时间
        String time = DateUtil.format(date, DatePattern.NORM_TIME_PATTERN);
        String time1 = DateUtil.format(date1, DatePattern.NORM_TIME_PATTERN);
        String time2 = date.toString(DatePattern.NORM_TIME_PATTERN);
        assertEquals(time, time1);
        assertEquals(time1, time2);
        // Fri Mar 31 19:22:42 CST 2023
        assertEquals(date1.toString(), date.toString(DatePattern.JDK_DATETIME_FORMAT));
    }

    @Test
    public void stringToTime() {
        String strDateTime = "1970-01-01 08:00:00";
        DateTime dateTime = DateUtil.parse(strDateTime);
        // 获取秒
        assertEquals(0, dateTime.second());
        // 获取微秒
        assertEquals(0, dateTime.millisecond());
        // 获取分
        assertEquals(0, dateTime.minute());
        // 获取时
        assertEquals(8, dateTime.hour(true));
        // 获取年
        assertEquals(70, dateTime.getYear());
        assertEquals(1970, DateUtil.year(dateTime));
        // 获取月份，1月份属于数组0
        assertEquals(0, dateTime.getMonth());
        assertEquals(0, DateUtil.month(dateTime));
        // 一个月的第几天
        assertEquals(1, dateTime.dayOfMonth());
        // 一周的第几天，星期四，西方周日是一周的第1天
        assertEquals(5, dateTime.dayOfWeek());
        // 一年的第几天
        assertEquals(1, dateTime.dayOfYear());
        // 格式化时间
        assertEquals("1970/01/01", dateTime.toString("yyyy/MM/dd"));
        assertEquals("1970/01/01", DateUtil.format(dateTime, "yyyy/MM/dd"));
        assertEquals("1970/01/01", dateTime.toString(new SimpleDateFormat("yyyy/MM/dd")));
        assertEquals("1970/01/01 08:00:00", dateTime.toString(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")));
        // 获取常用时间
        assertEquals("1970-01-01", DateUtil.formatDate(dateTime));
        assertEquals("1970-01-01 08:00:00", DateUtil.formatDateTime(dateTime));
        assertEquals("08:00:00", DateUtil.formatTime(dateTime));
        // 自定义格式解析
        assertEquals("2021/02/03", DateUtil.parse("2021@02@03", "yyyy@MM@dd").toString("yyyy/MM/dd"));
    }

    /**
     * 获取系统当前时间
     */
    @Test
    public void time() {
        String strDateTime = "1970-01-01 18:30:00";
        DateTime dateTime = DateUtil.parse(strDateTime);
        // now 当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
        assertEquals(DateUtil.date(System.currentTimeMillis()).toString("yyyy-MM-dd HH:mm:ss"), DateUtil.now());
        // toDay 当前日期字符串，格式：yyyy-MM-dd
        assertEquals(DateUtil.date(System.currentTimeMillis()).toString("yyyy-MM-dd"), DateUtil.today());
        // 获取当前时间的秒数
        assertEquals(System.currentTimeMillis() / 1000, DateUtil.currentSeconds());
        // 获取一天的开始时间或结束时间
        assertEquals("1970-01-01 00:00:00", DateUtil.beginOfDay(dateTime).toString());
        assertEquals("1970-01-01 23:59:59", DateUtil.endOfDay(dateTime).toString());
        // 获取前一天
        assertEquals("2021-02-28", DateUtil.offsetDay(DateUtil.parse("2021-03-01"), -1).toDateStr());
    }

    @Test
    public void business() {
        // 获取属相
        assertEquals("羊", DateUtil.getChineseZodiac(1991));
        // 获取星座
        assertEquals("巨蟹座", DateUtil.getZodiac(Month.JULY.getValue(), 17));
        // 获取年龄
        assertTrue(DateUtil.ageOfNow("1991-01-01") > 30);
        // 判断是否为闰年
        assertFalse(DateUtil.isLeapYear(2023));
    }

    @Test
    public void timestamp() {
        // 2023-08-16 15:18:10 时间戳是 1692170290
        String dateTime = "2023-08-16 15:18:10";
        // 东八区
        ZoneOffset zoneOffset = ZoneOffset.of("+8");
        assertEquals(1692170290, LocalDateTimeUtil.parse(dateTime, DatePattern.NORM_DATETIME_PATTERN).toEpochSecond(zoneOffset));
        // Date 获取的时间戳是毫秒
    }

    @Test
    public void offsetTime() {
        // 2023-08-16 15:18:10 时间戳是 1692170290
        String dateTime = "2023-08-16 15:18:10";
        long timestamp = 1692170290L;
        // 东八区
        ZoneOffset zoneOffset = ZoneOffset.of("+8");
        // 测试时间偏移
        long offsetSeconds = 8L;
        assertEquals(timestamp + offsetSeconds, LocalDateTimeUtil.parse(dateTime, DatePattern.NORM_DATETIME_PATTERN).plusSeconds(offsetSeconds).toEpochSecond(zoneOffset));
    }
}