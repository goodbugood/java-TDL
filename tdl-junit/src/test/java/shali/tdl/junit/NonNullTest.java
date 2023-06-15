package shali.tdl.junit;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

class User {
    @Getter
    private String name;

    public void setName(@NonNull String name) {
        this.name = name;
    }
}

@Slf4j
public class NonNullTest {
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    /**
     * // @NonNull 修饰的形参，如果为 null，会抛出 NPE
     */
    @Test
    public void nonNull() {
        User user = new User();
        expectedException.expect(NullPointerException.class);
        user.setName(null);
    }
}
