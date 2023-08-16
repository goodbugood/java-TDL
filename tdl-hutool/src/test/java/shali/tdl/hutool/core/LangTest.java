package shali.tdl.hutool.core;

import cn.hutool.core.lang.Pair;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class LangTest {
    /**
     * 多返回值测试
     */
    @Test
    public void pair() {
        Pair<Boolean, String> successPair = successPair();
        Assertions.assertTrue(successPair.getKey());
        Assertions.assertEquals("{\"name\":\"tony\"}", successPair.getValue());
        Pair<Boolean, String> failPair = failPair();
        Assertions.assertFalse(failPair.getKey());
        Assertions.assertEquals("对不起出错了", failPair.getValue());
    }

    private Pair<Boolean, String> successPair() {
        return new Pair<>(true, "{\"name\":\"tony\"}");
    }

    private Pair<Boolean, String> failPair() {
        return new Pair<>(false, "对不起出错了");
    }
}
