package shali.tdl.junit;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;

/**
 * 测试抛异常
 */
@Slf4j
public class ExpectedExceptionTest {
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    /**
     * 你可以断言下面的逻辑会抛出一种异常
     */
    @Test
    public void assertException() {
        Object o = null;
        // 断言下面会抛 NPE
        expectedException.expect(NullPointerException.class);
        o.toString();
    }

    /**
     * 断言抛出的异常的父类
     */
    @Test
    public void assertException2() {
        log.info("junit4 支持实例化断言异常类来断言异常：ExpectedException.none().expect(IndexOutOfBoundsException.class)");
        log.info("ExpectedException.none() 断言异常的方式，已被废弃，Since 4.13 Assert.assertThrows can be used to verify that your code throws a specific exception.");
        expectedException.expect(IndexOutOfBoundsException.class);
        throw new ArrayIndexOutOfBoundsException("你的数组访问越界了");
    }

    /**
     * 使用注解断言异常
     */
    @Test(expected = UnsupportedOperationException.class)
    public void assertException3() {
        log.info("junit4 支持注解断言异常：@Test(expected = UnsupportedOperationException.class)");
        throw new UnsupportedOperationException("这个方法没有实现，不能使用");
    }

    /**
     * 捕获的方式断言异常
     */
    @Test
    public void assertException4() {
        boolean thrown = false;
        try {
            throw new UnsupportedOperationException("这个方法还没实现");
        } catch (UnsupportedOperationException e) {
            thrown = true;
        }
        log.info("捕获异常，然后进行判断，也是断言异常的最普通做法。");

        Assert.assertTrue(true);
    }

    /**
     * 捕获的方式断言异常
     */
    @Test
    public void assertException5() {
        try {
            throw new UnsupportedOperationException("这个方法还没实现");
        } catch (UnsupportedOperationException e) {
            // todo 断言的异常的另一种方式
            // junit4 Assert.assertThrows()
            // junit5 Assertions.assertThrows()
            UnsupportedOperationException unsupportedOperationException = Assert.assertThrows(UnsupportedOperationException.class, () -> {
            });
            Assert.assertEquals("这个方法还没实现", unsupportedOperationException.getMessage());
        }
    }
}
