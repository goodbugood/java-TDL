package shali.tdl.jdk.lang.ref;

import org.junit.jupiter.api.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

import static org.junit.jupiter.api.Assertions.*;

class ReferenceTest {
    /**
     * 测试软引用
     * 如果 JVM 认为内存足够，它会保留软引用指向的对象；如果内存不足，软引用指向的对象会被垃圾回收。
     */
    @Test
    void testSoftReference() {
        SoftReference<String> softReference = new SoftReference<>("hello");
        // 未回收
        assertNotNull(softReference.get());
        // 回收内存
        System.gc();
        // 未回收，因为内存足够
        assertNotNull(softReference.get());
    }

    /**
     * 测试弱引用
     * 弱引用强度比软引用更弱一些，只要有垃圾回收，无论当前内存是否足够，弱引用指向的对象都会被回收。
     */
    @Test
    void testWeakReference() {
        String hello = "hello";
        WeakReference<String> weakReference = new WeakReference<>(hello);
        // 未回收
        assertEquals("hello", weakReference.get());
        // 将原始引用设置为 null，此时只有弱引用指向 hello
        hello = null;
        // 模拟内存压力，促使垃圾回收器工作
        System.gc();
        // 回收内存，因为运行了 gc
        // todo shali 很奇怪，这里不应该是被回收了吗？ - 2024/7/23
        assertNull(weakReference.get());
    }

    /**
     * 测试虚引用
     * 虚引用不能单独使用，必须和引用队列一起使用。
     * 一个对象是否有虚引用的存在，完全不会对其生存时间构成影响，也无法通过虚引用来获取一个对象实例。
     * 虚引用的 get() 方法总是返回 null，因此不能通过虚引用访问对象本身。虚引用主要用于接收对象被回收的通知。
     */
    @Test
    void testPhantomReference() {
        // 虚引用必须和引用队列一起使用
        ReferenceQueue<String> referenceQueue = new ReferenceQueue<>();
        PhantomReference<String> phantomReference = new PhantomReference<>("hello", referenceQueue);
        // 虚引用的 get() 方法总是返回 null，因此不能通过虚引用访问对象本身
        assertNull(phantomReference.get());
        // 虚引用还未加入引用队列
        assertNull(referenceQueue.poll());
        // todo shali 虚引用什么时候会被加入到引用队列呢？ - 2024/7/23
    }
}
