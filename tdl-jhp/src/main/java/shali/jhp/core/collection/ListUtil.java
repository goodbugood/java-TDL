package shali.jhp.core.collection;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Shali
 * @date 2024/02/22
 */
public class ListUtil {
    /**
     * 将集合 a 和集合 b 合并
     *
     * @param listA 集合 a
     * @param listB 集合 b
     * @return {@link List}<{@link T}>
     */
    public static <T> List<T> merge(List<T> listA, List<T> listB) {
        // 方式一：集合合并
        // List<T> list = new ArrayList<>(listA);
        // list.addAll(listB);
        // return list;
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

    private ListUtil() {
    }
}
