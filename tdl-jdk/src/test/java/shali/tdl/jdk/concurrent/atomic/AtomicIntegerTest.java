package shali.tdl.jdk.concurrent.atomic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 无需显示同步（如 synchronized）的原子操作
 *
 * @author Shali
 * @date 2024/09/10
 */
class AtomicIntegerTest {
    @Test
    void testAtomicInteger() {
        // 创建一个 AtomicInteger 对象，初始值为 0
        AtomicInteger atomicInteger = new AtomicInteger(0);

        // 创建 10 个线程，每个线程都会对 AtomicInteger 对象进行自增操作
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    atomicInteger.incrementAndGet();
                }
            });
            thread.start();
            try {
                // main 线程等待子线程结束
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // 打印最终结果：线程安全的情况下，累加 10 次，每次加 1000
        Assertions.assertEquals(10000, atomicInteger.get());
    }
}
