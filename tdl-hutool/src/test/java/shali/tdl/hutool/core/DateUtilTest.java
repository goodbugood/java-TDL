package shali.tdl.hutool.core;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import static org.junit.Assert.*;

import org.junit.Test;

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
}