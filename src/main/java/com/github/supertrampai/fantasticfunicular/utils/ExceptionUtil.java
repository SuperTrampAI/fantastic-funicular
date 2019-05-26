package com.github.supertrampai.fantasticfunicular.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @version V1.0
 * @program: fantastic-funicular
 * @description: TODO
 * @author: lxh800109@gmail.com
 * @create: 2019-05-25 18:45
 * 修改历史：
 * 时间           作者          版本        描述
 * ====================================================
 **/
public class ExceptionUtil {

    /**
     * 获取异常的堆栈信息
     *
     * @param t
     * @return
     */
    public static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        try {
            t.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }

}
