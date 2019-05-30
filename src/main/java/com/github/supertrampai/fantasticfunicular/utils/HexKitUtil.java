package com.github.supertrampai.fantasticfunicular.utils;

/**
 * @version V1.0
 * @program: fantastic-funicular
 * @description: TODO
 * @author: lxh800109@gmail.com
 * @create: 2019-05-30 15:10
 * 修改历史：
 * 时间           作者          版本        描述
 * ====================================================
 **/
public class HexKitUtil {

    public void Kex(){
        //十进制转成十六进制：
        Integer.toHexString(1);

        //十进制转成八进制
        Integer.toOctalString(1);
        //十进制转成二进制
        Integer.toBinaryString(1);
        // 十六进制转成十进制
        Integer.valueOf("FFFF",16).toString();
        //八进制转成十进制
        Integer.valueOf("876",8).toString();
        //二进制转十进制
        Integer.valueOf("0101",2).toString();
    }
    public static void main(String args[]) {
        int i = 10;
        System.out.println("十进制数 " + i + " 转换成十六进制为 " + Integer.toHexString(i));
        System.out
                .println("十进制数 " + i + " 转换成八进制为 " + Integer.toOctalString(i));
        System.out.println("十进制数 " + i + " 转换成二进制为 "
                + Integer.toBinaryString(i));
        String str = "A";
        System.out.println("十六进制数 " + str + " 转换成10进制为 "
                + Integer.parseInt(str, 16));
        str = "012";
        System.out.println("八进制数 " + str + " 转换成10进制为 "
                + Integer.parseInt(str, 8));
        str = "1010";
        System.out.println("二进制数 " + str + " 转换成10进制为 "
                + Integer.parseInt(str, 2));
    }

}
