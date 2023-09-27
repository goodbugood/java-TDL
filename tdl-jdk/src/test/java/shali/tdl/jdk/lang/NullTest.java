package shali.tdl.jdk.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NullTest {
    /**
     * 将 null 值或未初始化的变量赋值给基本类型会报 NPE
     * 赋值给引用类型就不会
     */
    @Test
    void convertInt() {
        Integer age1 = null;
        Assertions.assertThrows(NullPointerException.class, () -> {
            int age11 = age1;
        });
        Integer age11 = age1;
    }
}
