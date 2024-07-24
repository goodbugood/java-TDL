package shali.tdl.hutool.json;

import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONNull;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;
import shali.tdl.jdk.util.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class JSONUtilTest {
    /**
     * json 串转 Java bean
     */
    @Test
    void toBean() {
        String student = "{\"name\":\"tony\",\"age\":23}";
        Student studentBean = JSONUtil.toBean(student, Student.class);
        assertEquals("tony", studentBean.getName());
        assertEquals(23, studentBean.getAge());
        // 严格区分大小写，且不会自行移除下划线 _
        String student2 = "{\"Name\":\"tony\",\"a_ge\":23}";
        Student studentBean2 = JSONUtil.toBean(student2, Student.class);
        assertNull(studentBean2.getName());
        assertNull(studentBean2.getAge());
        // 不支持转两层的 json 数据
        String student3 = "{\"name\":\"tony\",\"age\":23,\"ScoreInfo\":{\"math\":56}}";
        Student studentBean3 = JSONUtil.toBean(student, Student.class);
        assertEquals("tony", studentBean3.getName());
        assertEquals(23, studentBean3.getAge());
        assertNull(studentBean3.getScoreInfo());
    }

    /**
     * Java Bean 转 json，忽略空值
     */
    @Test
    void parseObjNonNull() {
        Student student = new Student();
        student.setName("tony");
        // 解析 bean 时忽略空值
        JSONObject jsonObject = JSONUtil.parseObj(student, true);
        assertEquals("tony", jsonObject.get("name"));
        assertNull(jsonObject.get("age"));
        assertNull(jsonObject.get("scoreInfo"));
        String jsonStr = jsonObject.toString();
        assertEquals("{\"name\":\"tony\"}", jsonStr);
        // 解析 bean 时不忽略空值，json 按照 bean 属性顺序输出
        JSONObject jsonObject2 = JSONUtil.parseObj(student, false, true);
        assertEquals("tony", jsonObject2.get("name"));
        assertEquals(JSONNull.NULL, jsonObject2.get("age"));
        assertEquals(JSONNull.NULL, jsonObject2.get("scoreInfo"));
        assertEquals("{\"name\":\"tony\",\"age\":null,\"scoreInfo\":null}", jsonObject2.toString());
    }

    /**
     * 学习，练习 JSONConfig
     */
    @Test
    void jsonConfig() {
        JSONConfig jsonConfig = JSONConfig.create();
        // 按字段顺序输出，默认不按字段顺序输出
        jsonConfig.setOrder(true);
        // 忽略空值，默认不忽略
        jsonConfig.setIgnoreNullValue(true);
        Student student = new Student();
        student.setName("tony");
        JSONObject jsonObject = JSONUtil.parseObj(student, jsonConfig);
        assertEquals("tony", jsonObject.get("name"));
        assertNull(jsonObject.get("age"));
        // 如果不忽略空值，null 会被转换成 JSONNull，而不是 null
        jsonConfig.setIgnoreNullValue(false);
        JSONObject jsonObject1 = JSONUtil.parseObj(student, jsonConfig);
        assertEquals(null, jsonObject.get("age"));
        assertEquals(JSONNull.NULL, jsonObject1.get("scoreInfo"));
    }
}
