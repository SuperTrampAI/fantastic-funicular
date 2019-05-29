package com.github.supertrampai.fantasticfunicular.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @version V1.0
 * @program: fantastic-funicular
 * @description: TODO
 * @author: lxh800109@gmail.com
 * @create: 2019-05-28 23:39
 * 修改历史：
 * 时间           作者          版本        描述
 * ====================================================
 **/
@Component
public class NeoProperties {
    @Value("${com.github.supertrampai.title}")
    private String title;
    @Value("${com.github.supertrampai.description}")
    private String description;

    //省略getter settet方法

}