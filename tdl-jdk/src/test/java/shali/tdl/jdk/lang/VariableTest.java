package shali.tdl.jdk.lang;

import org.junit.jupiter.api.Test;
import shali.tdl.jdk.util.SystemUtils;

public class VariableTest {
    /**
     * 编码时尽量避免隐式类型转换，因为他消耗性能
     */
    @Test
    void typeConvert() {
        int num = 10000000;
        Integer sum = 0;
        SystemUtils.howLong("typeConvert1");
        for (int i = 0; i < num; i++) {
            sum += i;
        }
        SystemUtils.howLong("typeConvert2");
        int sum2 = 0;
        for (int i = 0; i < num; i++) {
            sum2 += i;
        }
        SystemUtils.howLong("typeConvert3");
        long time = SystemUtils.howLong("typeConvert2", "typeConvert1") - SystemUtils.howLong("typeConvert3", "typeConvert2");
        System.out.printf("在循环 %d 次中，隐式类型转换比不转换类型多耗时：%d ms%n", num, time);
    }
}
