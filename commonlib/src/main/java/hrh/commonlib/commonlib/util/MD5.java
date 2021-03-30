package hrh.commonlib.commonlib.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MD5 {

    private MD5() {
    }

    private static final char[] hexDigits =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String toHexString(byte[] bytes) {
        if (bytes == null) return "";
        StringBuilder hex = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            hex.append(hexDigits[(b >> 4) & 0x0F]);
            hex.append(hexDigits[b & 0x0F]);
        }
        return hex.toString();
    }

    public static String md5(String string) {
        byte[] encodeBytes;
        try {
            encodeBytes = MessageDigest.getInstance("MD5").digest(string.getBytes(Charset.forName("UTF-8")));
        } catch (NoSuchAlgorithmException neverHappened) {
            throw new RuntimeException(neverHappened);
        }
        return toHexString(encodeBytes);
    }
}