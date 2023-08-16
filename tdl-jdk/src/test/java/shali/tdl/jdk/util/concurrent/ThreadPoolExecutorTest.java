package shali.tdl.jdk.util.concurrent;

import cn.hutool.core.thread.ExecutorBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolExecutorTest {
    @Test
    public void addOneThread() {
        ThreadPoolExecutor threadPoolExecutor = ExecutorBuilder.create()
                // 初始线程数
                .setCorePoolSize(2)
                // 最大线程数
                .setMaxPoolSize(5)
                // 线程超时时间，单位秒
                .setKeepAliveTime(60)
                .build();
        Thread testThread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(LocalDateTime.now().getSecond());
        });
        threadPoolExecutor.execute(testThread);
        System.out.println(Thread.currentThread().getName());
        System.out.println(LocalDateTime.now().getSecond());
    }
}
