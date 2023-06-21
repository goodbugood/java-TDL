package shali.tdl.jdk.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class ArraysTest {
    /**
     * 对 ArrayList 进行元素的添加和删除操作，报不支持的操作异常
     */
    @Test(expected = UnsupportedOperationException.class)
    public void unsupportedOperationExceptionOfAdd() {
        log.warn("Arrays.asList.add() 会抛 UnsupportedOperationException，因为其返回的 ArrayList 是其自己内部的静态类，add 方法没有 override。");
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6);
        integerList.add(7);
    }

    /**
     * java.util.Arrays.ArrayList 没有重写 remove 方法
     */
    @Test(expected = UnsupportedOperationException.class)
    public void unsupportedOperationExceptionOfRemove() {
        log.warn("Arrays.asList.remove() 会抛 UnsupportedOperationException，因为其返回的 ArrayList 是其自己内部的静态类，remove 方法没有 override。");
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6);
        integerList.remove(0);
    }
}
