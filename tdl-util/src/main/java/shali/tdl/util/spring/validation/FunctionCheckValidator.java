package shali.tdl.util.spring.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @FunctionCheck 注解处理类
 * @author Shali
 * @date 2024/05/10
 */
public class FunctionCheckValidator implements ConstraintValidator<FunctionCheck, Object>  {
    private ValidatorFunction validatorFunction;

    /**
     * 获取我们注解里的参数 - 验证方法
     * Initializes the validator in preparation for
     * {@link #isValid(Object, ConstraintValidatorContext)} calls.
     * The constraint annotation for a given constraint declaration
     * is passed.
     * <p>
     * This method is guaranteed to be called before any use of this instance for
     * validation.
     * <p>
     * The default implementation is a no-op.
     *
     * @param constraintAnnotation annotation instance for a given constraint declaration
     */
    @Override
    public void initialize(FunctionCheck constraintAnnotation) {
        Class<? extends ValidatorFunction> checker = constraintAnnotation.checker();
        try {
            validatorFunction = checker.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 执行验证
     * Implements the validation logic.
     * The state of {@code value} must not be altered.
     * <p>
     * This method can be accessed concurrently, thread-safety must be ensured
     * by the implementation.
     *
     * @param value object to validate
     * @param context context in which the constraint is evaluated
     * @return {@code false} if {@code value} does not pass the constraint
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (null == value) {
            // null 的验证统统交给 NotNull 来验证，其他验证器只验证 not null
            return true;
        }
        return validatorFunction.validate(value);
    }
}
