package shali.tdl.jdk.util;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class CollectionTest {
    /**
     * 测试双端队列，其实就类似数组结构，可以进行首尾操作
     */
    @Test
    void testDeque() {
        Deque<Integer> deque = new ArrayDeque<>(2);
        deque.add(1);
        deque.add(5);
        assertEquals(2, deque.size());
        // peekFirst 返回队首元素
        assertEquals(1, deque.peekFirst());
        // pollFirst 移除并返回队首元素
        assertEquals(1, deque.pollFirst());
        // 1 元素被移除，只剩余 1 个元素 5
        assertEquals(1, deque.size());
        // peekLast 返回队尾元素
        assertEquals(5, deque.peekLast());
        // pollLast 移除并返回队尾元素
        assertEquals(5, deque.pollLast());
        assertEquals(0, deque.size());
        // 移除队首元素，如果队列为空抛出异常
        assertThrows(NoSuchElementException.class, deque::removeFirst);
    }

    /**
     * 先进先出队列 fifo
     */
    @Test
    void testFIFOQueue() {
        // ArrayBlockingQueue 的特点就是不会自动扩容
        Queue<Integer> integerQueue = new ArrayBlockingQueue<>(2);
        // add 添加元素
        integerQueue.add(1);
        integerQueue.add(5);
        // offer 添加元素，如果队列已满，返回 false
        assertFalse(integerQueue.offer(3));
        // poll 移除并返回队首元素
        assertEquals(1, integerQueue.poll());
        // peek 不移除队首元素，仅返回
        assertEquals(5, integerQueue.peek());
        // 移除 5
        assertEquals(5, integerQueue.poll());
        assertTrue(integerQueue.isEmpty());
        // 返回 null，由于队列中无元素了
        assertNull(integerQueue.peek());
        // 由于无元素可移除，抛出异常
        assertThrows(NoSuchElementException.class, integerQueue::remove);
    }

    /**
     * 测试空集合
     */
    @Test
    void testEmptyCollection() {
        // 声明空 list
        List<String> emptyList = Collections.emptyList();
        assertTrue(emptyList.isEmpty());
        // 声明空 map
        Map<String, Integer> emptyMap = Collections.emptyMap();
        assertTrue(emptyMap.isEmpty());
        // 声明空 set
        Set<Integer> emptySet = Collections.emptySet();
        assertTrue(emptySet.isEmpty());
    }

    /**
     * 测试集合流式操作，空指针异常
     * 数组 -> List -> Set 去重 -> List
     */
    @Test
    void streamNPE() {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 3);
        assertEquals(6, integerList.size());
        // 利用 set 的特性去重
        Set<Integer> integerSet = new HashSet<>(integerList);
        assertEquals(5, integerSet.size());
        // null 执行 stream 的异常
        List<Integer> integerList2 = null;
        assertThrows(NullPointerException.class, () -> {
            // 不推荐此方式
            // List<Integer> collect = integerList2.stream().collect(Collectors.toList());
            // 此处抛出 NPE 异常
            new ArrayList<>(integerList2);
        });
        // 使用 Optional 处理 npe 问题
        List<Integer> integerList1 = Optional.ofNullable(integerList2).orElse(Collections.emptyList());
        assertInstanceOf(List.class, integerList1);
        assertTrue(integerList1.isEmpty());
        // 避免集合中的元素是 null 造成的 npe
        Optional.of(integerList).ifPresent((o) -> assertEquals(6, o.size()));
        integerList = null;
        Optional.ofNullable(integerList).ifPresent((o) -> assertEquals(6, o.size()));
    }
}
