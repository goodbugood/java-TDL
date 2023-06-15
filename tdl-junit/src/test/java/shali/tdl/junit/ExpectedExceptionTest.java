package shali.tdl.junit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * 测试抛异常
 */
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
        expectedException.expect(IndexOutOfBoundsException.class);
        throw new ArrayIndexOutOfBoundsException("你的数组访问越界了");
    }
}
