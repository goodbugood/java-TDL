package shali.jhp.core.collection;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Shali
 * @date 2024/02/22
 */
public class ListUtil {
    /**
     * 将集合 a 和集合 b 合并
     * 方式一：集合合并
     * List<T> list = new ArrayList<>(listA);
     * listA.addAll(listB);
     * return listA;
     *
     * @param listA 集合 a
     * @param listB 集合 b
     * @return {@link List}<{@link T}>
     */
    public static <T> List<T> merge(List<T> listA, List<T> listB) {
        // 方式二：java 8 的 stream
        return Stream.concat(listA.stream(), listB.stream()).collect(Collectors.toList());
    }

    /**
     * 将集合 a 和集合 b 合并
     *
     * @param listA 集合 a
     * @param listB 集合 b
     * @param distinct 是否去重
     * @return {@link List}<{@link T}>
     */
    public static <T> List<T> merge(List<T> listA, List<T> listB, boolean distinct) {
        if (distinct) {
            return Stream.concat(listA.stream(), listB.stream()).distinct().collect(Collectors.toList());
        }
        return merge(listA, listB);
    }

    /**
     * 获取集合对象属性的集合，类似 php 的 array_column
     *
     * @param function 对象属性
     * @param list 集合
     * @return {@link List}<{@link R}>
     */
    public static <T, R> List<R> column(List<T> list, Function<T, R> function) {
        return list.stream().map(function).collect(Collectors.toList());
    }

    /**
     * List<T> 转 Map<String, T>
     *
     * @param function 字段
     * @param list 对象
     * @param <T> 对象
     * @param <R> 字段
     * @return 字段对象的集合
     */
    public static <T, R> Map<R, T> toMap(Function<T, R> function, List<T> list) {
        // 当 key 重复时，key2 覆盖 key1
        return list.stream().collect(Collectors.toMap(function, Function.identity(), (key1, key2) -> key2));
    }

    /**
     * List<T> 转 Map<String, String>
     *
     * @param function1 对象属性做 key
     * @param function2 对象属性做 value
     * @param list 集合
     * @return {@link Map}<{@link R1}, {@link R2}>
     */
    public static <T, R1, R2> Map<R1, R2> toMap(Function<T, R1> function1, Function<T, R2> function2, List<T> list) {
        return list.stream().collect(Collectors.toMap(function1, function2, (oldValve, newValve) -> newValve));
    }

    /**
     * List<T> 转 Map<String, List<T>>
     *
     * @param function 对象属性做 key 对象做 value
     * @param list 集合
     * @return {@link Map}<{@link R}, {@link List}<{@link T}>>
     */
    public static <T, R> Map<R, List<T>> groupToMap(Function<T, R> function, List<T> list) {
        return list.stream().collect(Collectors.groupingBy(function));
    }

    private ListUtil() {
    }
}
