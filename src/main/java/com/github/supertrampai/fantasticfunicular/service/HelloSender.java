package com.github.supertrampai.fantasticfunicular.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @version V1.0
 * @program: fantastic-funicular
 * @description: mq发送者
 * @author: lxh800109@gmail.com
 * @create: 2019-06-02 16:18
 * 修改历史：
 * 时间           作者          版本        描述
 * ====================================================
 **/
@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }
}
