package shali.tdl.jdk.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ArraysTest {
    /**
     * 对 ArrayList 进行元素的添加和删除操作，报不支持的操作异常
     */
    @Test(expected = UnsupportedOperationException.class)
    public void unsupportedOperationExceptionOfAdd() {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6);
        integerList.add(7);
    }

    /**
     * java.util.Arrays.ArrayList 没有重写 remove 方法
     */
    @Test(expected = UnsupportedOperationException.class)
    public void unsupportedOperationExceptionOfRemove() {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6);
        integerList.remove(0);
    }
}
