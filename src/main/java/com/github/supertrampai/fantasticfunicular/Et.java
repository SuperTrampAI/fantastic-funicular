package com.github.supertrampai.fantasticfunicular;


/**
 * Et
 *
 * @author yichuanzhou
 * 2017/5/8 16:13
 */
public class Et {

    public static void callEx(String msg) {
        throw new CusException(1, msg);
    }

    public static void invalidSeller() {
        throw new CusException(1001, "当前用户不是销售用户");
    }


}
