package shali.tdl.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class SecurityTest {
    private static final String origin = "welcome to guangzhou";

    private static final String base64 = "d2VsY29tZSB0byBndWFuZ3pob3U=";

    private static final String desKey = "12345678";

    public static final String encryptedHex = "46bcd3cbba74cd0b4d703914e8a3094871ba3c97cc8671a1";

    @Test
    public void base64Encode() {
        assertEquals(base64, Security.base64Encode(origin));
    }

    @Test
    public void base64Decode() {
        assertEquals(origin, Security.base64Decode(base64));
    }

    @Test
    public void desEncryptToHex() throws Exception {
        assertEquals(encryptedHex, Security.desEncryptToHex(desKey, origin));
    }

    @Test
    public void desDecryptHex() throws Exception {
        assertEquals(origin, Security.desDecryptHex(desKey, encryptedHex));
    }
}
