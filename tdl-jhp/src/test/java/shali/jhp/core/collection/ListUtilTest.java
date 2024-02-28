package shali.jhp.core.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class ListUtilTest {
    @Test
    void merge() {
        List<Integer> integerListA = Arrays.asList(1, 2, 3, 4);
        List<Integer> integerListB = Arrays.asList(3, 4, 5, 6);
        // 将 2 个 list 进行合并
        List<Integer> merge = ListUtil.merge(integerListA, integerListB);
        Assertions.assertEquals("[1, 2, 3, 4, 3, 4, 5, 6]", merge.toString());
    }

    @Test
    void mergeDistinct() {
        List<Integer> integerListA = Arrays.asList(1, 2, 3, 4);
        List<Integer> integerListB = Arrays.asList(3, 4, 5, 6);
        // 将 2 个 list 进行合并
        List<Integer> merge = ListUtil.merge(integerListA, integerListB, false);
        Assertions.assertEquals("[1, 2, 3, 4, 3, 4, 5, 6]", merge.toString());
        // 合并去重
        List<Integer> mergeDistinct = ListUtil.merge(integerListA, integerListB, true);
        Assertions.assertEquals("[1, 2, 3, 4, 5, 6]", mergeDistinct.toString());
    }
}