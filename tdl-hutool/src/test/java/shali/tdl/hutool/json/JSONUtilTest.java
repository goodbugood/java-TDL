package shali.tdl.hutool.json;

import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;
import shali.tdl.jdk.util.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class JSONUtilTest {
    /**
     * json 串转 Java bean
     */
    @Test
    public void toBean() {
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
}
