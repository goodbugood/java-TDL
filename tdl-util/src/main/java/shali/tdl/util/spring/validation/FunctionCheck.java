package shali.tdl.util.spring.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 函数验证器注解
 * 使用此注解，可以帮你调用你自己的验证器
 * @author Shali
 * @date 2024/05/10
 */
// 使用的范围，字段，方法返回值，参数，构造函数参数
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.CONSTRUCTOR})
// 注解的生命周期
@Retention(RetentionPolicy.RUNTIME)
// 就是说我这个注解，执行哪个验证器
@Constraint(validatedBy = FunctionCheckValidator.class)
public @interface FunctionCheck {
    /**
     * 验证方法，注意 JSR380 要求，自定义的约束规则注解参数不能 valid 开头
     * @return {@link Class}<{@link ?} {@link extends} {@link ValidatorFunction}>
     */
    Class<? extends ValidatorFunction> checker();

    //region 一个验证注解必备属性，虽然没有抽象接口来限定你必须配置这三个参数，但是应用时，会去检查注解的这三个参数，所以必须要有
    String message() default "参数通过校验函数校验不通过";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    //endregion
}
