package com.github.supertrampai.fantasticfunicular.think.guava;

import com.github.supertrampai.fantasticfunicular.domain.BeanDomain;
import com.google.common.base.Objects;
import org.junit.Test;

/**
 * @version V1.0
 * @program: fantastic-funicular
 * @description: TODO
 * @author: lxh800109@gmail.com
 * @create: 2019-05-26 14:14
 * 修改历史：
 * 时间           作者          版本        描述
 * ====================================================
 **/
public class GuavaObjectTest {

    @Test
    public void objectsEqualsTest(){

        /*
        * 原有Object.equals方法的局限：只能判断两个引用变量是否是同一个对象，
        * 而对于判断两个对象在逻辑上是否相等，需要看类的成员变量来判断两个类的实例是否相等，
        * 简单说：guava 的Objects.equal()方法实现了重写Object的各种复杂判断，当你要判断两个对象是否相等时，只要使用
        * 这个方法，就可以判断
        * */
        /*
        * 重写equals方法的要求：
        1、自反性：对于任何非空引用x，x.equals(x)应该返回true。
        2、对称性：对于任何引用x和y，如果x.equals(y)返回true，那么y.equals(x)也应该返回true。
        3、传递性：对于任何引用x、y和z，如果x.equals(y)返回true，y.equals(z)返回true，那么x.equals(z)也应该返回true。
        4、一致性：如果x和y引用的对象没有发生变化，那么反复调用x.equals(y)应该返回同样的结果。
        5、非空性：对于任意非空引用x，x.equals(null)应该返回false。
        * */
        //工具类，使用guava的equal方法来判断两个对象是否相等，无论是基本类型，还是对象
        Objects.equal("adf","adf");
        BeanDomain bean=new BeanDomain(1,"n1","woman");
        BeanDomain bean2=new BeanDomain(2,"n2","man");
        BeanDomain bean3=null;
        System.out.println(bean.equals(bean2));
        System.out.println(bean.equals(bean));
        //System.out.println(bean3.equals(null));这行代码是会报错：java.lang.NullPointerException
        System.out.println(Objects.equal(null,bean3));//使用guava就不会保持，可以正常判断，

        //使用Objects.equal(a,b) 可以省略复写equal方法是对于可能null值的分支判断，是代码更加优雅
    }
}
