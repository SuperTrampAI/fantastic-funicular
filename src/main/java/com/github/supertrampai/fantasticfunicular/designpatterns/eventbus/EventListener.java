package com.github.supertrampai.fantasticfunicular.designpatterns.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * @version V1.0
 * @program: fantastic-funicular
 * @description: 消息接收类：把消息对象转入消息接收类，赋值并返回
 * @author: lxh800109@gmail.com
 * @create: 2019-05-25 17:03
 * 修改历史：
 * 时间           作者          版本        描述
 * ====================================================
 **/
public class EventListener {

    public Integer lastMessage=0;

    @Subscribe
    public void listen(EventTest eventTest){
        lastMessage=eventTest.getMessage();
        System.out.println("Message:"+lastMessage);
    }
    public Integer getLastMessage(){
        return lastMessage;
    }

}
