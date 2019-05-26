package com.github.supertrampai.fantasticfunicular.utils;

import java.util.UUID;

/**
 * @version V1.0
 * @program: fantastic-funicular
 * @description: TODO
 * @author: lxh800109@gmail.com
 * @create: 2019-05-25 11:08
 * 修改历史：
 * 时间           作者          版本        描述
 * ====================================================
 **/
public class UUIDUtil {

    /**
     * 获取UUID
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

}
