package com.github.supertrampai.fantasticfunicular.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @version V1.0
 * @program: fantastic-funicular
 * @description: TODO
 * @author: lxh800109@gmail.com
 * @create: 2019-05-25 18:32
 * 修改历史：
 * 时间           作者          版本        描述
 * ====================================================
 **/
public class MD5Util {

    /**
     * md5加密
     * @param text
     * @return
     */
    public static String encode(String text){
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] buffer = digest.digest(text.getBytes());
            // byte -128 ---- 127
            return getString(buffer);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 任意文件转换成Md5
     * Can convert a text to MD5
     * @param in
     * @return md5
     */

    public static String encode(InputStream in) {
        try {
            MessageDigest digester = MessageDigest.getInstance("MD5");
            byte[] bytes = new byte[8192];
            int byteCount;
            while ((byteCount = in.read(bytes)) > 0) {
                digester.update(bytes, 0, byteCount);
            }
            byte[] digest = digester.digest();

            // byte -128 ---- 127
            return getString(digest);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
        }
        return null;
    }

    private static String getString(byte[] digest) {
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            int a = b & 0xff;
            // Log.d(TAG, "" + a);

            String hex = Integer.toHexString(a);

            if (hex.length() == 1) {
                hex = 0 + hex;
            }

            sb.append(hex);
        }

        return sb.toString();
    }

}
