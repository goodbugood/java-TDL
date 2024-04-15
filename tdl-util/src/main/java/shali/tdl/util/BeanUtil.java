package shali.tdl.util;

import cn.hutool.core.util.ObjectUtil;

import java.util.function.Consumer;

/**
 * Bean 处理
 *
 * @author Shali
 * @date 04/15/2024
 */
public class BeanUtil {
    private BeanUtil() {
    }

    /**
     * 判空赋值
     *
     * @param t 被赋值
     * @param v 赋值
     * @param <T> 赋值类型
     */
    public static <T> void setIfNotEmpty(Consumer<T> t, T v) {
        if (!ObjectUtil.isEmpty(v)) {
            t.accept(v);
        }
    }
}
