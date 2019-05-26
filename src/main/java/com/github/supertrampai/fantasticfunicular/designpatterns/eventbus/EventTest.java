package com.github.supertrampai.fantasticfunicular.designpatterns.eventbus;

/**
 * @version V1.0
 * @program: fantastic-funicular
 * @description: 消息封装类：定义消息对象，可以发送一些什么内容
 * @author: lxh800109@gmail.com
 * @create: 2019-05-25 17:00
 * 修改历史：
 * 时间           作者          版本        描述
 * ====================================================
 **/
public class EventTest {

    private final Integer message;

    public EventTest(Integer message) {
        this.message = message;
        System.out.println("event message"+message);
    }

    public Integer getMessage(){
        return message;
    }
}
