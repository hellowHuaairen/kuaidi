package com.wangzg.kuaidi.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by wangzhiguo on 2019/8/28 0028.
 * md5加密
 */

public class MD5Utils {
    private static MessageDigest mdigest = null;
    private static char digits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private static MessageDigest getMdInst() {
        if (null == mdigest) {
            try {
                mdigest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return mdigest;
    }

    public static String encode(String s) {
        if(null == s) {
            return "";
        }

        try {
            byte[] bytes = s.getBytes();
            getMdInst().update(bytes);
            byte[] md = getMdInst().digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = digits[byte0 >>> 4 & 0xf];
                str[k++] = digits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
