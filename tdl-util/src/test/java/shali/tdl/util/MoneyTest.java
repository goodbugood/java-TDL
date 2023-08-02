package shali.tdl.util;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class MoneyTest {
    private static Money money;

    @Before
    public void setUp() throws Exception {
        money = Money.valueOf("1");
    }

    @Test
    public void valueOf() {
        assertTrue(Money.valueOf(new BigDecimal("0")) instanceof Money);
    }

    @Test
    public void testValueOf() {
        assertTrue(Money.valueOf(1L) instanceof Money);
    }

    @Test
    public void testValueOf1() {
        assertTrue(Money.valueOf(1d) instanceof Money);
    }

    @Test
    public void testValueOf2() {
        assertTrue(Money.valueOf("0") instanceof Money);
    }

    @Test
    public void add() {
        Money money = Money.valueOf("0.25");
        Money money1 = Money.valueOf("0.75");
        assertEquals(1, money.add(money1).longValue());
        assertEquals("0.25", money.stringValue());
        assertEquals("0.75", money1.stringValue());
    }

    @Test
    public void sub() {
        Money money = Money.valueOf("1");
        Money money1 = Money.valueOf("0.09");
        assertEquals("0.91", money.sub(money1).stringValue());
        assertEquals(1, money.longValue());
        assertEquals(0.09, money1.doubleValue(), 0);
    }

    @Test
    public void mul() {
        Money money1 = Money.valueOf(10);
        assertEquals("10", money.mul(money1).stringValue());
        assertEquals(1, money.longValue());
        assertEquals(10, money1.longValue());
    }

    @Test
    public void div() {
        Money money = Money.valueOf(1.5);
        Money money1 = Money.valueOf(3);
        assertEquals("0.5", money.div(money1).stringValue());
        assertEquals("1.5", money.stringValue());
        assertEquals("3", money1.stringValue());
    }

    @Test
    public void stringValue() {
        assertEquals("1", money.stringValue());
    }

    @Test
    public void doubleValue() {
        assertEquals(1d, money.doubleValue(), 0);
    }

    @Test
    public void longValue() {
        assertEquals(1L, money.longValue());
    }

    @Test
    public void compareTo() {
        assertEquals(0, money.compareTo(Money.valueOf(1)));
        assertEquals(1, money.compareTo(Money.valueOf(0.5)));
        assertEquals(-1, money.compareTo(Money.valueOf(2)));
    }

    @Test
    public void testEquals() {
        assertTrue(money.equals(Money.valueOf(0.99D), 0.01));
        assertTrue(money.equals(Money.valueOf(1L), 0.01));
        assertFalse(money.equals(Money.valueOf(0.98), 0.01));
        assertFalse(money.equals(Money.valueOf(1.02), 0.01));
        // 比较完后原数据是不能变的
        Money money1 = Money.valueOf(1.5);
        assertFalse(money.equals(money1, 0.01));
        assertEquals("1", money.stringValue());
        assertEquals("1.5", money1.stringValue());
    }

    @Test
    public void sum() {
        Money money = Money.valueOf("0.25");
        Money money1 = Money.valueOf("0.75");
        assertEquals(1, money.sum(money1).longValue());
        assertSame(money, money.sum(money1));
    }
}
