package shali.tdl.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * 一个简单货币计算
 *
 * @author Shali
 */
public class Money {
    /**
     * 核心就是bigdecimal
     */
    private BigDecimal bigDecimal;

    private static final int SCALE = 10;

    private static final MathContext MC = new MathContext(SCALE, RoundingMode.HALF_UP);

    private Money(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public static Money valueOf(BigDecimal bigDecimal) {
        return new Money(bigDecimal);
    }

    public static Money valueOf(double d) {
        // BigDecimal.valueOf 会将 double 转成字符串，再调用 BigDecimal 的字符串构造方法
        // 等价 new BigDecimal(Double.toString(d))
        return valueOf(BigDecimal.valueOf(d));
    }

    public static Money valueOf(long l) {
        return valueOf(BigDecimal.valueOf(l));
    }

    public static Money valueOf(String str) {
        return valueOf(new BigDecimal(str));
    }

    /**
     * BigDecimal每次计算都会返回一个新值，所以一定要记得存储
     */
    public Money add(Money money) {
        return Money.valueOf(this.bigDecimal.add(money.bigDecimal, MC));
    }

    /**
     * 精确的减法
     */
    public Money sub(Money money) {
        return Money.valueOf(this.bigDecimal.subtract(money.bigDecimal, MC));
    }

    /**
     * 精确的乘法
     */
    public Money mul(Money money) {
        return Money.valueOf(this.bigDecimal.multiply(money.bigDecimal, MC));
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，默认精确到小数点以后10位，以后的数字四舍五入
     *
     * @param money 被除数
     * @return 返回
     */
    public Money div(Money money) {
        return Money.valueOf(this.bigDecimal.divide(money.bigDecimal, MC));
    }

    /**
     * 实现一直累计加减，不会返回新对象
     *
     * @param money 被累计的金额
     * @return 返回原对象
     */
    public Money sum(Money money) {
        bigDecimal = bigDecimal.add(money.bigDecimal, MC);

        return this;
    }

    /*获取其他类型的值*/

    /**
     * 比较2个金额的大小，大于返回1，相等返回0，小于返回-1
     */
    public int compareTo(Money money) {
        return bigDecimal.compareTo(money.bigDecimal);
    }

    /**
     * 比较两个金额是否相等，允许指定 delta 的误差
     * 比如开票，赠送金额，允许指定误差
     *
     * @param money 被比较的值
     * @param delta 允许的误差
     * @return 返回比较结果
     */
    public boolean equals(Money money, double delta) {
        if (0 == compareTo(money)) {
            return true;
        }

        return Math.abs(this.sub(money).doubleValue()) <= delta;
    }

    @Override
    public String toString() {
        return stringValue();
    }

    public String stringValue() {
        return bigDecimal.toString();
    }

    public BigDecimal bigDecimalValue() {
        return bigDecimal;
    }

    public double doubleValue() {
        return bigDecimal.doubleValue();
    }

    public long longValue() {
        return bigDecimal.longValue();
    }
}
