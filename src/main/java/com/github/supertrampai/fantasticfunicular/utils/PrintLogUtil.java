package com.github.supertrampai.fantasticfunicular.utils;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


/**
 * @version V1.0
 * @program: fantastic-funicular
 * @description: TODO
 * @author: lxh800109@gmail.com
 * @create: 2019-05-25 19:03
 * 修改历史：
 * 时间           作者          版本        描述
 * ====================================================
 **/
@Component
public class PrintLogUtil {

    /**
     * 控制台输出级别（false-0不输出，error-1输出异常【默认】，info-2输出重要信息及异常，debug-3输出所有）.
     */
    private static String console;

    /**
     * 输出级别.
     */
    private static Integer level = Integer.MIN_VALUE;

    /**
     * 输出级别-error.
     */
    private static String levelError = "error";

    /**
     * 输出级别-info.
     */
    private static String levelInfo = "info";

    /**
     * 输出级别-debug.
     */
    private static String levelDebug = "debug";

    /**
     * 输出级别-default.
     */
    private static String levelDefault = "default";

    /**
     * 使用ThreadLocal解决SimpleDateFormat不同步问题.
     */
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    /**
     * 直接输出所有信息.
     *
     * @param param
     *            param
     */
    public static void print(Object param) {
        dealPrint(param, levelDefault);
    }

    /**
     * 异常.
     *
     * @param param
     *            param
     */
    public static void printError(Object param) {
        if (1 <= level) {
            dealPrint(param, levelError);
        }
    }

    /**
     * 重要信息.
     *
     * @param param
     *            param
     */
    public static void printInfo(Object param) {
        if (2 <= level) {
            dealPrint(param, levelInfo);
        }
    }

    /**
     * debug.
     *
     * @param param
     *            param
     */
    public static void printDebug(Object param) {
        if (3 <= level) {
            dealPrint(param, levelDebug);
        }
    }

    /**
     * 分类处理输出.
     *
     * @param param
     *            param
     * @param levelDesc
     *            levelDesc
     */
    private static void dealPrint(Object param, String levelDesc) {
        if (null == param) {
            print("null", levelDesc);
        } else if (param instanceof String) {
            print((String) param, levelDesc);
        } else if (param instanceof Throwable) {
            print((Throwable) param);
        } // 可增加打印Bean
    }

    /**
     * 输出字符串.
     *
     * @param s
     *            字符串
     * @param levelDesc
     *            levelDesc
     */
    private static void print(String s, String levelDesc) {
        StringBuilder builder = new StringBuilder(s.length() + 32);
        builder.append("[").append(levelDesc).append("]:[").append(threadLocal.get().format(new Date())).append("]").append(s);
        System.out.println(builder.toString());
    }

    /**
     * 输出异常.
     *
     * @param e
     *            e
     */
    private static void print(Throwable e) {
        print("---Position---");
        // String print = locateException(e, 0) + locateException(e, 1); // 方便单独记录日志
        // System.out.println(print);
        e.printStackTrace();
    }

    /**
     * 构造异常信息（文件名、方法名、行号）.
     *
     * @param e
     *            异常
     * @param i
     *            异常父类级别
     * @return 异常信息
     */
    @SuppressWarnings("unused")
    private static String locateException(Throwable e, int i) {
        StringBuilder builder = new StringBuilder(30);
        if (e != null && null != e.getStackTrace() && i >= 0 && e.getStackTrace().length > i) {
            builder.append(">>>").append(e.getStackTrace()[i].getFileName()).append("-").append(e.getStackTrace()[i].getMethodName()).append(e.getStackTrace()[i].getLineNumber());
        }
        return builder.toString();
    }

    /**
     * 初始化日志级别.
     *
     */
    private static synchronized void initLevel() {
        if (Integer.MIN_VALUE != level) {
            return;
        }
        if (Objects.equals("false", console)) {
            level = 0;
        } else if (Objects.equals(levelDebug, console)) {
            level = 3;
        } else if (Objects.equals(levelInfo, console)) {
            level = 2;
        } else {
            level = 1;
        }
    }

    /**
     * 初始化异常级别（未配置console时，console==null）.
     *
     * @param console
     *            异常级别
     */
    //@Value("${console}")
    private void setConsole(String console) {
        PrintLogUtil.console = console;
        initLevel();
    }

}
