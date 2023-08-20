package shali.tdl.jdk.util.concurrent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class ThreadPoolExecutorTest {
    /**
     * Java 内置的线程池
     */
    @Test
    void addOneThread() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 3, 60, TimeUnit.NANOSECONDS, new LinkedBlockingQueue<>(10));
        Assertions.assertTrue(threadPoolExecutor instanceof ThreadPoolExecutor);
        Thread testThread = new Thread(() -> {
            System.out.printf("%s - %d%n", Thread.currentThread().getName(), LocalDateTime.now().getSecond());
        });
        threadPoolExecutor.execute(testThread);
        System.out.printf("%s - %d%n", Thread.currentThread().getName(), LocalDateTime.now().getSecond());
    }
}
