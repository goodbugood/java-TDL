package shali.tdl.util;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 发票相关安全，比如加解密，签名，验签算法
 *
 * @author Shali
 */
public class Security {
    /**
     * base64编码字符串
     *
     * @param str 需要被编码的字符串
     */
    public static String base64Encode(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 解密base64字符串
     */
    public static String base64Decode(String base64Str) {
        // todo  为什么不能Arrays.toString()
        return new String(Base64.getDecoder().decode(base64Str), StandardCharsets.UTF_8);
    }

    /**
     * 将字符串des加密，并转成16进制字符串
     */
    public static String desEncryptToHex(String desKey, String strData) throws Exception {
        byte[] key = desKey.getBytes(StandardCharsets.UTF_8);
        byte[] str = strData.getBytes(StandardCharsets.UTF_8);
        // 生成随机数
        SecureRandom secureRandom = new SecureRandom();
        // 创建密钥对象
        DESKeySpec desKeySpec = new DESKeySpec(key);
        // 利用密钥工厂将密钥对象转成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        // 使用Cipher对象完成加密
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, secureRandom);

        return new String(Hex.encodeHex(cipher.doFinal(str)));
    }

    /**
     * 将16进制字符串进行des解密
     */
    public static String desDecryptHex(String desKey, String decryptStr) throws Exception {
        byte[] key = desKey.getBytes(StandardCharsets.UTF_8);
        byte[] str = Hex.decodeHex(decryptStr);
        // 生成随机数
        SecureRandom secureRandom = new SecureRandom();
        // 创建密钥对象
        DESKeySpec desKeySpec = new DESKeySpec(key);
        // 利用密钥工厂将密钥对象转成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        // 使用Cipher对象完成加密
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, secureRandom);

        return new String(cipher.doFinal(str));
    }
}
