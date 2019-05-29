package com.github.supertrampai.fantasticfunicular.action;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version V1.0
 * @program: fantastic-funicular
 * @description: TODO
 * @author: lxh800109@gmail.com
 * @create: 2019-05-28 23:24
 * 修改历史：
 * 时间           作者          版本        描述
 * ====================================================
 **/
@RestController
public class HelloWorldController {
    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }
}
