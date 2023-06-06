package shali.tdl.jdk.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SystemUtilsTest {

    @Test
    void usedMemory() {
        SystemUtils.usedMemory("start");
    }

    @Test
    void testUsedMemory() {
        int num = 100000;
        SystemUtils.usedMemory("start");
        for (int i = 0; i < num; i++) {
            String tony = new String("tony");
        }
        SystemUtils.usedMemory("end");
        assertTrue(SystemUtils.usedMemory("end", "start") > 0);
        System.out.printf("new %d 个字符串，消耗内存：%d byte%n", num, SystemUtils.usedMemory("end", "start"));
    }

    @Test
    void howLong() {
        SystemUtils.howLong("start");
    }

    @Test
    void testHowLong() {
        SystemUtils.howLong("start");
        int num = 100000;
        for (int i = 0; i < num; i++) {
        }
        SystemUtils.howLong("end");
        assertTrue(SystemUtils.howLong("end", "start") > 0);
        System.out.printf("循环 %d 次，耗时 %d ms%n", num, SystemUtils.howLong("end", "start"));
    }
}