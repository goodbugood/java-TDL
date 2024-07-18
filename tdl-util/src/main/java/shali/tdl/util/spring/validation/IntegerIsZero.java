package shali.tdl.util.spring.validation;

import cn.hutool.core.util.ObjectUtil;

/**
 * 验证函数实现类
 * 检查被注解的属性值是否为 0
 * @author Shali
 * @date 2024/05/15
 */
public class IntegerIsZero implements ValidatorFunction {
    @Override
    public boolean validate(Object value) {
        return ObjectUtil.equals(0, value);
    }
}
