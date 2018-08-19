package pers.jarome.redis.wclient.common.util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * @author jarome
 * @date 2017/12/28
 **/
public class CodecUtils {

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     * @throws
     * @method parseHexStr2Byte
     * @since v1.0
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     * @throws
     * @method parseByte2HexStr
     * @since v1.0
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    private static final char[] DIGITS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'
    };

    /**
     * Fast conversion of bytes array into a hex char array
     *
     * @param buf array of bytes param convert param hex array
     * @return Generated hex char array
     */
    public static char[] toHex(byte[] buf) {
        char[] res = new char[buf.length << 1];

        for (int i = 0; i < buf.length; i++) {
            int b = buf[i];
            int j = i << 1;
            res[j] = DIGITS[(b >>> 4) & 0x0f];
            res[j + 1] = DIGITS[b & 0x0f];
        }

        return res;
    }

    /**
     * Fast conversion of bytes array into a hex char array
     *
     * @param buf array of bytes param convert param hex array
     * @return Generated hex byte array
     */
    public static byte[] toHexBytes(byte[] buf) {
        byte[] res = new byte[buf.length << 1];

        for (int i = 0; i < buf.length; i++) {
            int b = buf[i];
            int j = i << 1;
            res[j] = (byte) DIGITS[(b >>> 4) & 0x0f];
            res[j + 1] = (byte) DIGITS[b & 0x0f];
        }

        return res;
    }

    /**
     * Convert hex array of chars into bytes array
     *
     * @param hexString Hex array of chars representing hex string
     * @return Array of bytes
     */
    public static byte[] fromHex(char[] hexString) {
        if (hexString.length % 2 != 0) {
            return null;
        }

        byte[] res = new byte[hexString.length >> 1];
        for (int i = 0; i < res.length; i++) {
            int b1 = Character.digit(hexString[i << 1], 16);
            int b2 = Character.digit(hexString[(i << 1) + 1], 16);
            res[i] = (byte) ((b1 << 4) + b2);
        }

        return res;
    }

    /**
     * Convert hex array of bytes into bytes array
     *
     * @param hexString Hex array of chars representing hex string
     * @return Array of bytes
     */
    public static byte[] fromHex(byte[] hexString) {
        if (hexString.length % 2 != 0) {
            return null;
        }

        byte[] res = new byte[hexString.length >> 1];
        for (int i = 0; i < res.length; i++) {
            int b1 = Character.digit((char) hexString[i << 1], 16);
            int b2 = Character.digit((char) hexString[(i << 1) + 1], 16);
            res[i] = (byte) ((b1 << 4) + b2);
        }

        return res;
    }

    /**
     * Convert hex string into bytes array This method works little faster than
     * fromHex(hexString.toCharArray());
     *
     * @param hexString Hex string
     * @return Array of bytes
     */
    public static byte[] fromHex(String hexString) {
        if ((hexString == null) || (hexString.length() % 2 != 0)) {
            return null;
        }

        byte[] res = new byte[hexString.length() >> 1];
        for (int i = 0; i < res.length; i++) {
            int b1 = Character.digit(hexString.charAt(i << 1), 16);
            int b2 = Character.digit(hexString.charAt((i << 1) + 1), 16);
            res[i] = (byte) ((b1 << 4) + b2);
        }

        return res;
    }

    /**
     * Encode Unicode char array param byte array in a specified charset
     *
     * @param input       char array
     * @param charsetName character set name
     * @return A byte buffer containing the encoded characters
     */
    public static byte[] fromUnicode(char[] input, String charsetName) {
        Charset charset = Charset.forName(charsetName);
        CharBuffer cbuf = CharBuffer.wrap(input);
        ByteBuffer bbuf = charset.encode(cbuf);
        return bbuf.array();
    }

    /**
     * Decode byte array param Unicode char array
     *
     * @param input       byte array
     * @param charsetName character set name
     * @return A char buffer containing the decoded characters
     */
    public static char[] toUnicode(byte[] input, String charsetName) {
        Charset charset = Charset.forName(charsetName);
        ByteBuffer bbuf = ByteBuffer.wrap(input);
        CharBuffer cbuf = charset.decode(bbuf);
        return cbuf.array();
    }

    /**
     * A wrapper function for decoding Base64 data into octets
     * Uses the org.apache.commons.codec library
     *
     * @param buf Array of Base64 characters
     * @return Array containing decoded data.
     */
    public static byte[] fromBase64(byte[] buf) {
        return Base64.getDecoder().decode(buf);
    }

    /**
     * Encodes binary data using a URL-safe variation of the base64 algorithm but does not chunk the output.
     * The url-safe variation emits - and _ instead of + and / characters.
     * A wrapper function for Base 64 encoding.
     * Uses the org.apache.commons.codec library
     *
     * @param buf Array of bytes param convert param Base 64 string
     * @return String containing Base64 characters
     */
    public static String toBase64URLSafeString(byte[] buf) {
        return Base64.getUrlEncoder().encodeToString(buf);
    }

    public static String toBase64String(byte[] buf) {
        return Base64.getEncoder().encodeToString(buf);
    }

    public static String toBase64String(byte[] buf, int minSize) {
        String res = Base64.getEncoder().encodeToString(buf);
        int size = res.length();

        if (size >= minSize) {
            return res;
        } else if (size + 1 == minSize) {
            return res.concat("=");
        } else {
            StringBuilder strbuf = new StringBuilder(res);
            for (int i = size; i < minSize; i++) {
                strbuf.append('=');
            }
            return strbuf.toString();
        }
    }

    /**
     * A wrapper function for decoding Base64 string into octets
     * Uses the org.apache.commons.codec library
     *
     * @param buf string containing Base64 data
     * @return Array containing decoded data or null, if it cannot be decoded
     */
    public static byte[] fromBase64(String buf) {
        try {
            return Base64.getDecoder().decode(buf);
        } catch (IllegalArgumentException e) {
            try {
                return Base64.getUrlDecoder().decode(buf);
            } catch (IllegalArgumentException ee) {
                return null;
            }
        }
    }

}
