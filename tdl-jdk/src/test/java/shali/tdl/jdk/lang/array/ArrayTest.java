package shali.tdl.jdk.lang.array;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * 2023年2月24日 19点13分
 * 学习数组的特性
 */
public class ArrayTest {
    /**
     * 数组的几种定义形式
     */
    @Test
    public void arrayDefine() {
        // 定义即初始化方式一
        int[] ints = {1, 2,};
        // 定义初始化方式二
        int[] ints1 = new int[]{1, 2};
        // 先定义，再初始化
        int[] ints2;
        ints2 = new int[]{1, 2};
        assertArrayEquals(ints, ints1);
        assertArrayEquals(ints1, ints2);
    }

    /**
     * 验证数组是一个对象
     */
    @Test
    public void arrayIsObject() {
        Integer[] arr = new Integer[3];
        // 数组就是对象
        assertTrue(arr instanceof Object);
        // 如果 arr 是对象，他就会有长度属性
        assertSame(3, arr.length);
        Integer[] arr2 = new Integer[]{4, 5, 6};
        assertSame(3, arr2.length);
        // 基本类型数组
        int[] arr3 = new int[]{1, 2, 3};
        assertTrue(arr3 instanceof Object);
        assertSame(3, arr3.length);
        // 基本数据类型数组也是对象
        int[] arr4 = {1, 2, 3};
        assertTrue(arr4 instanceof Object);
        assertEquals(3, arr4.length);
    }

    /**
     * 数组转集合
     */
    @Test
    public void arrayTransferCollection() {
        // 包装数据类型转集合
        Integer[] integers = new Integer[]{1, 2};
        List<Integer> integerList = Arrays.stream(integers).collect(Collectors.toList());
        assertEquals(2, integerList.size());
        assertEquals(1, (int) integerList.get(0));
        assertEquals(2, (int) integerList.get(1));
        // 基本数据类型数组转集合
        int[] ints = new int[]{1, 2, 3};
        List<Integer> integerList1 = Arrays.stream(ints).boxed().collect(Collectors.toList());
        assertEquals(3, integerList1.size());
        assertEquals(1, (int) integerList1.get(0));
        assertEquals(3, (int) integerList1.get(2));
    }
}
