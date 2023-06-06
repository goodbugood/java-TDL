package shali.tdl.jdk.lang;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringTest {
    /**
     * new String() 是错误的用法，不仅浪费内存，还浪费时间，因为 "" 本身就是字符串，还要再包裹一次
     */
    @Test
    void newString() {
        // 这种使用字符串常量池的方式，其返回的已经是字符串类了。
        String name = "tony";
        assertInstanceOf(String.class, name);
        int loopCount = 100000;
        // 比较内存大小
        long nowFreeMemory = Runtime.getRuntime().freeMemory();
        long now = System.currentTimeMillis();
        for (int i = 0; i < loopCount; i++) {
            // 包装字符串，只会浪费时间和内存
            String newName = new String("tony");
        }
        long errorUsageFreeMemory = Runtime.getRuntime().freeMemory();
        long nowErrorUsage = System.currentTimeMillis();
        for (int i = 0; i < loopCount; i++) {
            String newName = "tony";
        }
        long rightUsageFreeMemory = Runtime.getRuntime().freeMemory();
        long nowRightUsage = System.currentTimeMillis();
        assertTrue((nowErrorUsage - now) > (nowRightUsage - nowErrorUsage));
        System.out.printf("循环 %d 次，错误初始化字符串，比正确多耗时 %d ms%n", loopCount, ((nowErrorUsage - now) - (nowRightUsage - nowErrorUsage)));
        assertTrue((nowFreeMemory - errorUsageFreeMemory) > (errorUsageFreeMemory - rightUsageFreeMemory));
        System.out.printf("循环 %d 次，错误初始化字符串，比正确多耗内存 %d byte%n", loopCount, ((nowFreeMemory - errorUsageFreeMemory) - (errorUsageFreeMemory - rightUsageFreeMemory)));
    }

    /**
     * 低效率和高效率的字符串正则的匹配
     * 低效率使用不经耗时还浪费时间
     */
    @Test
    void pattern() {
        String msg = "welcome to guangzhou";
        String regex = ".*guang.*";
        int loopCount = 1000;
        // 错误用法，我们看 java.lang.String.matches 源码发现，每次都会编译 1 次，正则表达式
        long now = System.currentTimeMillis();
        for (int i = 0; i < loopCount; i++) {
            assertTrue(msg.matches(regex));
        }
        long nowErrorUsage = System.currentTimeMillis();
        // 高效用法，仅需要编译 1 次，所以使用频率高的正则，最好加入到类常量比较好，类加载的时候编译，后期只管使用
        Pattern pattern = Pattern.compile(regex);
        for (int i = 0; i < loopCount; i++) {
            assertTrue(pattern.matcher(msg).matches());
        }
        long nowRightUsage = System.currentTimeMillis();
        assertTrue(nowRightUsage - nowErrorUsage < nowErrorUsage - now);
        System.out.printf("循环 %d 次，仅编译一次正则比循环中编译正则节省 %d ms%n", loopCount, (nowErrorUsage - now) - (nowRightUsage - nowErrorUsage));
    }
}
