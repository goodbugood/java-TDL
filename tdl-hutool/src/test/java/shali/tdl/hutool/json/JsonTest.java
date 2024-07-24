package shali.tdl.hutool.json;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import org.junit.Test;
import shali.tdl.jdk.util.Student;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * json 测试
 *
 * @author Shali
 * @date 2024/07/24
 */
public class JsonTest {
    @Test
    public void test() {
        // 按索引位置存放元素
        int[] ints = new int[30];
        ints[23] = 23;
        assertEquals(23, ints[23]);
        // 转 map
        List<Student> studentList = new ArrayList<>(240);
        Map<Integer, Object> objectMap = new HashMap<>(2);
        Student student = new Student();
        student.setAge(23);
        student.setName("我23岁");
        studentList.add(student);
        objectMap.put(student.getAge(), student);
        Student student1 = new Student();
        student1.setAge(34);
        student1.setName("我34岁了");
        studentList.add(student1);
        objectMap.put(student1.getAge(), student1);
        List<Integer> integerList = studentList.stream().map(Student::getAge).collect(Collectors.toList());
        System.out.println(integerList);
        List<String> stringList = studentList.stream().map(Student::getName).collect(Collectors.toList());
        System.out.println(stringList);
        int[] ids = {1, 2, 4};
        Student[] studentArr = new Student[2];
        studentArr[0] = student;
        studentArr[1] = student1;
        System.out.println(Arrays.toString(studentArr));
    }

    /**
     * 测试 json 编码
     */
    @Test
    public void jsonArray2UnboxArray() {
        // JSONArray 转基本类型数组
        String json = "[2, 3, 5]";
        JSONArray jsonArray = JSONUtil.parseArray(json);
        // 先转包装类型数组
        List<Integer> integerList = jsonArray.toList(Integer.class);
        // 然后集合转转数组
        int[] ints1 = integerList.stream().mapToInt(Integer::intValue).toArray();
        int[] ints = {2, 3, 5};
        assertArrayEquals(ints, ints1);

        Object[] objects = jsonArray.toArray();
        Integer[] integers = Arrays.copyOf(objects, objects.length, Integer[].class);

        // 方法二：
        // Object[] objects = jsonArray.toArray();
        int[] ints2 = new int[objects.length];
        for (int i = 0; i < objects.length; i++) {
            ints2[i] = (int) objects[i];
        }
        assertArrayEquals(ints, ints2);
    }
}
