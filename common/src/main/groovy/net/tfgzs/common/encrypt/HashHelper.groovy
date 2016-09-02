package net.tfgzs.common.encrypt

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class HashHelper {
    public static String MD5(String input) {
        return Encrypt(input, "MD5", false);
    }

    public static String MD5(String input, boolean igonreCase) {
        return Encrypt(input, "MD5", igonreCase);
    }

    public static String SHA1(String input) {
        return Encrypt(input, "SHA-1", false);
    }

    public static String SHA1(String input, boolean igonreCase) {
        return Encrypt(input, "SHA-1", igonreCase);
    }

    public static String SHA256(String input) {
        return Encrypt(input, "SHA-256", false);
    }

    public static String SHA256(String input, boolean igonreCase) {
        return Encrypt(input, "SHA-256", igonreCase);
    }

    /**
     * 对字符串加密,加密算法使用MD5,SHA-1,SHA-256,默认使用SHA-256
     * @param strSrc 要加密的字符串
     * @param encName 加密类型
     * @param igonreCase 是否忽略大小写
     * @return
     */
    public static String Encrypt(String strSrc, String encName, boolean igonreCase) {
        MessageDigest md = null;
        String strDes = null;
        if (igonreCase) {
            strSrc = strSrc.toUpperCase();
        }
        byte[] bt = strSrc.getBytes();
        try {
            if (encName == null || encName.equals("")) {
                encName = "SHA-256";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }
}
