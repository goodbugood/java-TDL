package shali.tdl.jdk.time;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

class TimeTest {
    /**
     * 获取时间戳，通常为 10 位的，秒
     */
    @Test
    void timestamp() {
        ZoneOffset zoneOffset = ZoneOffset.of("+8");
        String now = "2023-08-16 20:18:10";
        long nowInt = 1692188290L;
        String pattern = "yyyy-MM-dd HH:mm:ss";
        // 方式一：利用 java.time.LocalDateTime
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        Assertions.assertEquals(nowInt, LocalDateTime.parse(now, dateTimeFormatter).toEpochSecond(zoneOffset));
        //
        LocalDate.parse(now, dateTimeFormatter);
        // 方式二：利用 cn.hutool.core.date.LocalDateTimeUtil
        // Assertions.assertEquals(nowInt, LocalDateTimeUtil.parse(now, pattern).toEpochSecond(zoneOffset));
    }

    /**
     * 获取时间戳，毫秒
     */
    @Test
    void timestampMillisecond() {
        ZoneOffset zoneOffset = ZoneOffset.of("+8");
        String now = "2023-08-16 20:18:10";
        long nowMillisecond = 1692188290000L;
        String pattern = "yyyy-MM-dd HH:mm:ss";
        // 方式一：获取当前时间戳
        // Assertions.assertEquals(nowMillisecond, DateUtil.parse(now, pattern).toTimestamp().toInstant().toEpochMilli());
        // 方式二：获取系统时间戳毫秒
        Assertions.assertEquals(Instant.now().getEpochSecond(), System.currentTimeMillis() / 1000);
    }

    /**
     * 时间戳转 LocalDateTime
     */
    @Test
    void timestampToLocalDateTime() {
        long nowMillisecond = 1692188290000L;
        // 时间戳转瞬间类
        Instant instant = Instant.ofEpochMilli(nowMillisecond);
        // 瞬间类转 DateTime
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("+8"));
        Assertions.assertEquals("2023-08-16T20:18:10", localDateTime.toString());
        // 方法二：先转成时区 ZoneDateTime，再转 LocalDateTime
        ZonedDateTime zonedDateTime = Instant.ofEpochMilli(nowMillisecond).atZone(ZoneId.systemDefault());
        LocalDateTime localDateTime1 = zonedDateTime.toLocalDateTime();
        Assertions.assertEquals("2023-08-16T20:18:10", localDateTime1.toString());
    }
}
