package shali.tdl.jdk.lang;

import lombok.extern.slf4j.Slf4j;

import static org.junit.Assert.*;

import org.junit.Test;

@Slf4j
public class IntegerTest {
    /**
     * Integer.class 载入内存时，已经
     */
    @Test
    public void tinyIntCache() {
        Integer i1 = -128;
        Integer i2 = Integer.valueOf(-128);
        assertSame(i1, i2);
        Integer i3 = 127;
        Integer i4 = Integer.valueOf(127);
        assertSame(i3, i4);
        log.info("-128~127 使用了整型缓存");
        Integer i5 = 128;
        Integer i6 = Integer.valueOf(128);
        assertNotSame(i5, i6);
        assertNotSame(Integer.valueOf(-129), Integer.valueOf(-129));
        assertSame(Integer.valueOf(-128), Integer.valueOf(-128));
        log.info("整型超过 -128~127 就不能使用整型缓存");
    }

    /**
     * Integer 类型转字符串，不能使用 (String) 进行转换，会报错
     */
    @Test
    public void castString() {
        // String str = (String) Integer.valueOf(123);
        log.warn("(String) Integer.valueOf(123) 这种语法，整型强转字符串报错：java: 不兼容的类型: java.lang.Integer无法转换为java.lang.String");
        Integer integer = Integer.valueOf(123);
        assertEquals("123", integer.toString());
        assertEquals("123", String.valueOf(integer));
        log.info("Integer 类型转字符串，建议使用 .toString 或者 String.valueOf()");
    }
}
