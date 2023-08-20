package shali.tdl.jdk.lang;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.concurrent.*;

public class ThreadTest {
    static class ThreadA extends Thread {
        @Override
        public void run() {
            System.out.println("我是继承Thread的线程A");
        }
    }

    static class ThreadB implements Runnable {
        @Override
        public void run() {
            System.out.println("我是实现Runnable接口的线程B");
        }
    }

    static class ThreadD implements Callable<List<Integer>> {
        @Override
        public List<Integer> call() throws Exception {
            System.out.println("我是实现Callable接口的线程C");
            return null;
        }
    }

    /**
     * 测试线程的实现方式：5种
     */
    @Test
    public void newThread() {
        // 方式一
        new ThreadA().start();
        // 方式二
        new Thread(new ThreadB()).start();
        // 方式三：
        Thread threadC = new Thread() {
            @Override
            public void run() {
                super.run();
                System.out.println("我是匿名线程：" + this.getName());
            }
        };
        Assertions.assertTrue(threadC instanceof Runnable);
        threadC.setName("线程C");
        threadC.start();
        // 方式四：FutureTask 实现了 Runnable 接口
        ThreadD threadD = new ThreadD();
        FutureTask<List<Integer>> futureTask = new FutureTask<>(threadD);
        new Thread(futureTask).start();
        System.out.printf("主线程：%s%n", Thread.currentThread().getName());
    }

    @Test
    public void lambdaThread() {
        // 方式五：利用线程池，配置线程创建后的回调对象
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Assertions.assertTrue(executorService instanceof ExecutorService);
        executorService.submit(() -> System.out.println("我是线程池的 Runnable 实现"));
        // 方式六：也是线程池，不过配置的对象实现接口不同
        executorService.execute(() -> {
            System.out.println("我是线程池的 Callable 实现");
        });
    }
}
