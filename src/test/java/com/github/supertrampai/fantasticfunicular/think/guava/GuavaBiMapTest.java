package com.github.supertrampai.fantasticfunicular.think.guava;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Test;

/**
 * @version V1.0
 * @program: fantastic-funicular
 * @description: 使用Guava BiMap
 * @author: lxh800109@gmail.com
 * @create: 2019-05-26 13:35
 * 修改历史：
 * 时间           作者          版本        描述
 * ====================================================
 **/
public class GuavaBiMapTest {

    @Test
    public void BimapTest(){
        BiMap<Integer,String> userMap = HashBiMap.create();
        userMap.put(1,"name1");
        userMap.put(2,"name2");
        userMap.put(3,"name3");
        System.out.println("userMap:"+userMap);
        BiMap<String,Integer> newUserMap = userMap.inverse();
        System.out.println("newUserMap:"+newUserMap);
        //实现key ，value 值的反转
    }
    @Test
    public void BimapTest2(){
        BiMap<Integer,String> userMap = HashBiMap.create();
        userMap.forcePut(1,"name1");
        userMap.forcePut(2,"name2");
        userMap.forcePut(3,"name3");
        userMap.forcePut(4,"name4");
        userMap.forcePut(5,"name4");
        System.out.println("userMap:"+userMap);
        BiMap<String,Integer> newUserMap = userMap.inverse();
        System.out.println("newUserMap:"+newUserMap);
    }

    // inverse()fh方法详解
    /*
    * 对于inverse反转以后的newBiMap不是一个全新的Map，而是基于实现了一种试图关联，也就是说对于反转后的Map的操作都会影响原来的map
    * */
    @Test
    public void BimapTest3(){

        BiMap<Integer,String> userMap = HashBiMap.create();
        userMap.put(1,"name1");
        userMap.put(2,"name2");
        userMap.put(3,"name3");
        System.out.println("userMap:"+userMap);
        BiMap<String,Integer> newUserMap= userMap.inverse();
        newUserMap.put("name4",4);
        System.out.println("newUserMap:"+newUserMap);
        System.out.println("userMap:"+userMap);
        /*
        *
        * 输出：
        * userMap:{1=name1, 2=name2, 3=name3}
        * newUserMap:{name1=1, name2=2, name3=3, name4=4}
        * userMap:{1=name1, 2=name2, 3=name3, 4=name4}
        *
        * */


    }

}
