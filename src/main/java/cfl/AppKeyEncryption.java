package cfl;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AppKeyEncryption {

    static final String hmacSHA256Algorithm = "HmacSHA256";
    static final String app_secret = "<channel-app-secret>";
    static final String app_key = "<channel-app-key>";

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        System.out.println(appSecretSymmetricEncryption(hmacSHA256Algorithm,app_key,app_secret).toLowerCase());
    }

    public static String appSecretSymmetricEncryption(String algorithm, String app_key, String app_secret)
            throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(app_secret.getBytes(), algorithm);
        Mac mac = Mac.getInstance(algorithm);
        mac.init(secretKeySpec);
        return bytesToHex(mac.doFinal(app_key.getBytes()));
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

}
