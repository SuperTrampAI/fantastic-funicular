package com.github.supertrampai.fantasticfunicular.designpatterns.eventbus;

import com.google.common.eventbus.EventBus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @version V1.0
 * @program: fantastic-funicular
 * @description: TODO
 * @author: lxh800109@gmail.com
 * @create: 2019-05-25 17:06
 * 修改历史：
 * 时间           作者          版本        描述
 * ====================================================
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEventBus {

    @Test
    public void testReceiveEvent() throws Exception {

        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();

        eventBus.register(listener);

        eventBus.post(new EventTest(200));
        eventBus.post(new EventTest(300));
        eventBus.post(new EventTest(400));

        System.out.println("LastMessage:"+listener.getLastMessage());
    }


}
