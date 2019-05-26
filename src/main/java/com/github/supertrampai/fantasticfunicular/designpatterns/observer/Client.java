package com.github.supertrampai.fantasticfunicular.designpatterns.observer;

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
public class Client {
    public static void main(String[] args) {
        SubscriptionSubject mSubscriptionSubject=new SubscriptionSubject();
        //创建微信用户
        WechatObserver user1=new WechatObserver("name1");
        WechatObserver user2=new WechatObserver("naem2");
        WechatObserver user3=new WechatObserver("name3");
        //订阅公众号
        mSubscriptionSubject.attach(user1);
        mSubscriptionSubject.attach(user2);
        mSubscriptionSubject.attach(user3);
        //公众号更新发出消息给订阅的微信用户
        mSubscriptionSubject.notify("一乐行更新《如何看待中国的户籍制度》");
    }
}
