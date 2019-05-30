package com.github.supertrampai.fantasticfunicular.think.juc;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @version V1.0
 * @program: fantastic-funicular
 * @description: TODO
 * @author: lxh800109@gmail.com
 * @create: 2019-05-30 15:22
 * 修改历史：
 * 时间           作者          版本        描述
 * ====================================================
 **/
public class AtomicIntegerDemo {

    public static void main(String[] args) {
        SortedSet set=new TreeSet();
        set.add(11);
        set.add(4);
        set.add(7);
        set.add(1);
        for (Object obj:set) {
            System.out.println(obj);
        }
    }

}
