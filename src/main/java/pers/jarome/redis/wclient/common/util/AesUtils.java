package pers.jarome.redis.wclient.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;

/**
 * AES加密工具类
 *
 * @author jarome
 * @date 2017/12/22
 **/
public class AesUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(AesUtils.class);

    public static String PADDING = "AES/CBC/PKCS5Padding";

    public static Integer APP_IV_LEN = 16;

    /**
     * 加密方法
     *
     * @param data    要加密的数据
     * @param key     加密key
     * @param iv      加密iv
     * @param padding 数据填充方式
     * @return 加密的结果
     */
    public static String encode(String data, String key, String iv, String padding) {
        try {
            //算法/模式/补码方式
            Cipher cipher = Cipher.getInstance(padding);
            int blockSize = cipher.getBlockSize();
            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);
            return CodecUtils.parseByte2HexStr(encrypted);
        } catch (Exception e) {
            LOGGER.error("encode err", e);
            return null;
        }
    }

    /**
     * 解密方法
     *
     * @param content 加密内容
     * @param key     加密key
     * @param iv      加密iv
     * @param padding 数据填充方式
     * @return 原数据
     */
    public static String decode(String content, String key, String iv, String padding) {
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance(padding);
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), padding);
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] contentBytes = CodecUtils.parseHexStr2Byte(content);
            if (contentBytes == null) {
                return null;
            }
            byte[] original = cipher.doFinal(contentBytes);
            //去除结尾的0
            int i = original.length - 1;
            for (; i >= 0; i--) {
                byte bi = original[i];
                if (bi != 0) {
                    break;
                }
            }
            byte[] res = new byte[i + 1];
            System.arraycopy(original, 0, res, 0, i + 1);
            return new String(res);
        } catch (Exception e) {
            LOGGER.error("decode err", e);
            return null;
        }
    }

}
