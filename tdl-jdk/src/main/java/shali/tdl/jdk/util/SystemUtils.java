package shali.tdl.jdk.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shali
 */
public class SystemUtils {
    private static final Map<String, Long> MEMORY_MAP = new HashMap<>(128);

    private static final Map<String, Long> TIMESTAMP_MAP = new HashMap<>(128);

    /**
     * 记录当前标记处的内存空余
     *
     * @param mark 标记空闲内存
     */
    public static void usedMemory(String mark) {
        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        MEMORY_MAP.put(mark, usedMemory);
    }

    /**
     * 获取两次标记间内存的占用
     *
     * @param nextMark 下一个标记
     * @param previousMark 上一个标记
     * @return 内存占用，单位 byte
     */
    public static long usedMemory(String nextMark, String previousMark) {
        long nextMarkUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        MEMORY_MAP.put(nextMark, nextMarkUsedMemory);
        Long previousMarkUsedMemory = MEMORY_MAP.get(previousMark);
        return nextMarkUsedMemory - previousMarkUsedMemory;
    }

    /**
     * 标记当前时间戳，单位毫秒
     *
     * @param mark 标记
     */
    public static void howLong(String mark) {
        TIMESTAMP_MAP.put(mark, System.currentTimeMillis());
    }

    /**
     * 获取两处标记的耗时
     *
     * @param nextMark 下个标记
     * @param previousMark 上一个标记
     * @return 占用时间，单位毫秒
     */
    public static long howLong(String nextMark, String previousMark) {
        long nextMarkTime = System.currentTimeMillis();
        MEMORY_MAP.put(nextMark, nextMarkTime);
        Long previousMarkTime = TIMESTAMP_MAP.get(previousMark);
        return nextMarkTime - previousMarkTime;
    }
}
