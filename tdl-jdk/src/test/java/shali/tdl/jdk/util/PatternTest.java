package shali.tdl.jdk.util;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PatternTest {
    /**
     * 测试完整匹配和部分匹配
     */
    @Test
    void testMatches() {
        String str = "2024-07-29 19:00";
        String regex = "\\d{4}";
        Matcher matcher = Pattern.compile(regex).matcher(str);
        // 必须整个字符串是“年-月-日”格式
        assertFalse(matcher.matches());
        // 等效上面的步骤
        assertFalse(Pattern.matches(regex, str));
        // 只要有一个“年-月-日”格式即可
        assertFalse(matcher.find());
        // matches() 匹配器的游标已经移动到了字符串的末尾。此时再调用find()，它会从当前位置（即字符串末尾）开始查找匹配项
        matcher.reset();
        assertTrue(matcher.find());
    }
}
