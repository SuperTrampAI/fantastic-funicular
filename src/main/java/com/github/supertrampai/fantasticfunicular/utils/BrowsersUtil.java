package com.github.supertrampai.fantasticfunicular.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @version V1.0
 * @program: fantastic-funicular
 * @description: TODO
 * @author: lxh800109@gmail.com
 * @create: 2019-05-25 18:41
 * 修改历史：
 * 时间           作者          版本        描述
 * ====================================================
 **/
public class BrowsersUtil {

    public static boolean isMobile(HttpServletRequest request) {
        String browser = request.getHeader("user-agent");
        return browser != null && ((browser.contains("Android") && browser.contains("Mobile")) || browser.contains("iPhone"));
    }

    /**
     * @since 5.1.1
     */
    public static String getCSSClass(HttpServletRequest request) {
        return isIPhone(request)?"iphone":"";
    }

    private static boolean isIPhone(HttpServletRequest request) {
        String browser = request.getHeader("user-agent");
        return browser != null && browser.contains("iPhone");
    }

    /**
     * 获取设备版本
     * @param request
     * @return
     */
    public static String getDeviceVersion(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent");
        String deviceStr="";
        try {
            if (userAgent.contains("Version")){
                String tmp = userAgent.substring(userAgent.lastIndexOf("Version"),
                        userAgent.lastIndexOf("Version")+12).replace("/"," ");
                return tmp;
            }
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 获取设备名称
     * @param request
     * @return
     */
    public static String getDeviceName(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent");
        String deviceStr="";
        try {
            if(StringUtil.isNotEmpty(userAgent)){
                int startIndex=userAgent.indexOf("(");
                int endIndex=userAgent.indexOf(")");
                deviceStr= userAgent.substring(startIndex+1, endIndex);
                return deviceStr;
            }
        } catch (Exception e) {
        }
        return "";
    }


}
