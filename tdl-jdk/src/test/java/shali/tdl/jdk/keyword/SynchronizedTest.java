package shali.tdl.jdk.keyword;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 同步锁 synchronized 关键词测试
 */
@Slf4j
public class SynchronizedTest {
    private final static List<Integer> BOX = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));

    private synchronized void sync(Thread thread) {
        while (!BOX.isEmpty()) {
            Integer integer = BOX.get(0);
            String msg = String.format("【TS】我是线程：%s，我获得数字：%d", Thread.currentThread().getName(), integer);
            log.info(msg);
            BOX.remove(0);
        }
    }

    private void nonSync(Thread thread) {
        while (!BOX.isEmpty()) {
            Integer integer = BOX.get(0);
            String msg = String.format("【NTS】我是线程：%s，我获得数字：%d", Thread.currentThread().getName(), integer);
            log.info(msg);
            BOX.remove(0);
        }
    }

    /**
     * 安全线程
     */
    @Test
    public void threadSafe() throws InterruptedException {
        log.info("我是线程：{}，我启动了", Thread.currentThread().getName());
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        // 创建线程A
        Thread threadA = new Thread() {
            @Override
            public void run() {
                super.run();
                sync(this);
            }
        };
        threadA.setName("Thread-A");
        // 创建线程B
        Thread threadB = new Thread() {
            @Override
            public void run() {
                super.run();
                sync(this);
            }
        };
        threadA.setName("Thread-B");
        cachedThreadPool.submit(threadA);
        cachedThreadPool.submit(threadB);
        log.warn("控制台黑窗口要打印子线程的输出，main线程必须把持住console，不能先于子线程运行结束。");
        Thread.sleep(1000);
    }

    /**
     * 不安全线程
     * 由于无锁，所以会存在，多个线程获取到相同的数字
     */
    @Test
    public void nonThreadSafe() throws InterruptedException {
        log.info("我是线程：{}，我启动了", Thread.currentThread().getName());
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        // 创建线程A
        Thread threadA = new Thread() {
            @Override
            public void run() {
                super.run();
                nonSync(this);
            }
        };
        threadA.setName("Thread-A");
        // 创建线程B
        Thread threadB = new Thread() {
            @Override
            public void run() {
                super.run();
                nonSync(this);
            }
        };
        threadA.setName("Thread-B");
        cachedThreadPool.submit(threadA);
        cachedThreadPool.submit(threadB);
        log.warn("控制台黑窗口要打印子线程的输出，main线程必须把持住console，不能先于子线程运行结束。");
        Thread.sleep(1000);
    }
}
