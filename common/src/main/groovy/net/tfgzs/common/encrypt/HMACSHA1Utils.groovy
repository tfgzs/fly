package net.tfgzs.common.encrypt

import org.apache.commons.codec.binary.Base64

import javax.crypto.Mac
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

/**
 * Created by liutengfei on 2016/6/3 0003.
 */
class HMACSHA1Utils {

    public static String encode(String macData, String macKey) throws Exception {
        Mac mac = Mac.getInstance("HMACSHA1");
        byte[] secretByte = macKey.getBytes("UTF-8");
        byte[] dataBytes = macData.getBytes("UTF-8");
        SecretKey secret = new SecretKeySpec(secretByte, "HMACSHA1");
        mac.init(secret);
        byte[] doFinal = mac.doFinal(dataBytes);
        String checksum = Base64.encodeBase64String(doFinal);
        return checksum;
    }
}
