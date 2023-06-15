package shali.tdl.hutool.core;

import cn.hutool.core.convert.Convert;
import org.junit.Assert;
import org.junit.Test;

/**
 * 测试字符串转换
 */
public class ConvertTest {
    /**
     * 一个全角字符暂用2个字符
     */
    @Test
    public void sbc2DBC() {
        String abc = "abc";
        Assert.assertEquals(3, abc.length());
        // 半角转全角后就是 6 个字符
        Assert.assertEquals(6, Convert.toSBC(abc).length());
    }

    /**
     * 字符转十六进制
     */
}
