package com.github.supertrampai.fantasticfunicular.utils;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

/**
 * @version V1.0
 * @program: fantastic-funicular
 * @description: TODO
 * @author: lxh800109@gmail.com
 * @create: 2019-05-26 13:23
 * 修改历史：
 * 时间           作者          版本        描述
 * ====================================================
 **/
public class InverseMapUtil {

    /**
     * 逆转Map的key和value,无法避免Key，和Value的重复问题
     * @param <S>
     * @param <T>
     * @param map
     * @return
     */
    public static <S,T> Map<T,S> getInverseMap(Map<S,T> map) {
        Map<T,S> inverseMap = new HashMap<T,S>();
        for(Map.Entry<S,T> entry: map.entrySet()) {
            inverseMap.put(entry.getValue(), entry.getKey());
        }
        return inverseMap;
    }

    public static void main(String[] agrs){
        Map<Integer,String> logfileMap = Maps.newHashMap();
        logfileMap.put(1,"a.log");
        logfileMap.put(2,"b.log");
        logfileMap.put(3,"c.log");

        System.out.println("logfileMap:"+logfileMap);

        Map<String,Integer> logfileInverseMap = Maps.newHashMap();

        logfileInverseMap=getInverseMap(logfileMap);
        System.out.println("logfileInverseMap:"+logfileInverseMap);
    }

}
