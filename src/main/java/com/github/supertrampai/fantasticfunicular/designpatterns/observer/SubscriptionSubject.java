package com.github.supertrampai.fantasticfunicular.designpatterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0
 * @program: fantastic-funicular
 * @description: TODO
 * @author: lxh800109@gmail.com
 * @create: 2019-05-26 16:30
 * 修改历史：
 * 时间           作者          版本        描述
 * ====================================================
 **/
public class SubscriptionSubject implements Subject {
    //储存订阅公众号的微信用户
    private List<Observer> wechatUserList = new ArrayList<Observer>();

    @Override
    public void attach(Observer observer) {
        wechatUserList.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        wechatUserList.remove(observer);
    }

    @Override
    public void notify(String message) {
        for (Observer observer : wechatUserList) {
            observer.update(message);
        }
    }
}
