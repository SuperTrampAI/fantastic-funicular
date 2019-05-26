package com.github.supertrampai.fantasticfunicular.designpatterns.observer;

/**
 * @version V1.0
 * @program: fantastic-funicular
 * @description: 具体观察者：
 * @author: lxh800109@gmail.com
 * @create: 2019-05-26 16:28
 * 修改历史：
 * 时间           作者          版本        描述
 * ====================================================
 **/
public class WechatObserver implements Observer {
    // 微信用户名
    private String name;
    public WechatObserver(String name) {
        this.name = name;
    }
    @Override
    public void update(String message) {
        System.out.println(name + "-" + message);
    }
}
