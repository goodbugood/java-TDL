package shali.tdl.hutool.cache;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TimeCacheTest {
    /**
     * 缓存超时时间 2s
     */
    private static final Long TTL_MILLISECOND = 2 * 1000L;

    /**
     * 过期检查频率 1s
     */
    private static final Long CHECK_TIMEOUT_MILLISECOND = 1000L;

    /**
     * 构造缓存
     */
    private static final TimedCache<String, String> timedCache = CacheUtil.newTimedCache(TTL_MILLISECOND);

    @BeforeAll
    static void beforeAll() {
        // 开启缓存定时清理
        timedCache.schedulePrune(CHECK_TIMEOUT_MILLISECOND);
        // 配置缓存过期回收事件
        timedCache.setListener((key, cachedObject) -> {
            System.out.println(StrUtil.format("key={}&value={} 已过期被回收，触发回收事件", key, cachedObject));
        });
    }

    /**
     * 测试使用
     *
     * @throws InterruptedException 线程睡眠熔断
     */
    @Test
    void testNewTimedCache() throws InterruptedException {
        // 未赋值的时候里面是空的，size 是 0
        Assertions.assertEquals(0, timedCache.size());
        timedCache.put("name", "tony");
        // 添加元素后，未过期前是 1
        Assertions.assertEquals(1, timedCache.size());
        Assertions.assertEquals("tony", timedCache.get("name"));
        Thread.sleep(3 * 1000);
        // 这里断言成功，说明不是惰性删除，过期且到了清理时间就清除
        Assertions.assertEquals(0, timedCache.size());
        Assertions.assertNull(timedCache.get("name"));
        // 过期后元素个数是 0
        Assertions.assertEquals(0, timedCache.size());
        timedCache.put("age", "22", 1000);
        Thread.sleep(1000);
    }
}
