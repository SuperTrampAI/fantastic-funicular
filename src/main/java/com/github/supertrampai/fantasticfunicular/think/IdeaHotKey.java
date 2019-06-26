package com.github.supertrampai.fantasticfunicular.think;

import java.util.ArrayList;
import java.util.Objects;

public class IdeaHotKey {

    private Integer age;
    private Integer name;
    private String test;


    /* Alt + Insert select：Constructor ， Ctrl + A select all param */
    public IdeaHotKey(Integer age, Integer name, String test) {
        this.age = age;
        this.name = name;
        this.test = test;
    }

    /* Alt + Insert select：Getter And Setter ， Ctrl + A select all param */
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "Test{" +
                "age=" + age +
                ", name=" + name +
                ", test='" + test + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {


        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdeaHotKey test1 = (IdeaHotKey) o;
        return age.equals(test1.age) &&
                name.equals(test1.name) &&
                test.equals(test1.test);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name, test);
    }

    public static void main(String[] agrs) {

        String s = "123";
        String test = s;
        if (true) {


            System.out.println("ser");
        } else {

        }

        try {
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        System.out.println("safflower");// Ctrl+ Shift + U 全部轉換為小寫然后在大小写之间转换

    }
//    windows + insert ：改变光标的作用，使只可以移动，无法换行等操作


}
