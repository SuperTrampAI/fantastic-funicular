package com.github.supertrampai.fantasticfunicular.think;

import com.google.common.base.Optional;

/**
 * @version V1.0
 * @program: fantastic-funicular
 * @description: TODO
 * @author: lxh800109@gmail.com
 * @create: 2019-05-25 15:20
 * 修改历史：
 * 时间           作者          版本        描述
 * ====================================================
 **/
public class GuavaDemo {

    public static void main(String [] str){
        //创建Optional的三种方式
        //1.创建指定引用的Optional实例，若引用为null则快速失败
        Optional<Integer> possible1 = Optional.of(5);//若为null，抛出java.lang.NullPointerException
        //2.创建指定引用的Optional实例，若引用为null则表示缺失
        Optional<Integer> possible2 = Optional.fromNullable(null);//若为null，不会抛出异常
        //3.创建引用缺失的Optional实例
        Optional<Object> possible3=Optional.absent();

        //demo-method
        //如果Optional包含非null的引用（引用存在），返回true
        possible1.isPresent();
        //返回Optional所包含的引用，若引用缺失，则抛出java.lang.IllegalStateException
        possible1.get();
        //返回Optional所包含的引用，若引用缺失，返回指定的值
        possible1.or(1);
        //返回Optional所包含的引用，若引用缺失，返回null
        possible1.orNull();
        //返回Optional所包含引用的单例不可变集，如果引用存在，返回一个只有单一元素的集合，如果引用缺失，返回一个空集合。
        possible1.asSet();

        // Optional解决了什么问题：强迫开发者去思考可能出现null 的情况

        //---------------------------------

    }

}
