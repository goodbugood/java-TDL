package shali.tdl.hutool.collection;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtilTest {
    /**
     * 空集合检查
     */
    @Test
    public void check() {
        // CollectionUtil 是 CollUtil 的别名类
        List<String> stringList = new ArrayList<>();
        assertTrue(CollectionUtil.isEmpty(stringList));
        assertTrue(CollUtil.isEmpty(stringList));
        stringList = null;
        assertTrue(CollectionUtil.isEmpty(stringList));
        assertTrue(CollUtil.isEmpty(stringList));
    }
}
