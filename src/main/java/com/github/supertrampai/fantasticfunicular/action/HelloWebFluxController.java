/*
 * Copyright (c) 2017- 2019 SuperTrampAI.github All Rights Reserved.
 */

package com.github.supertrampai.fantasticfunicular.action;

/**
 * @Description
 * @auther lxh800109@gmail.com
 * @create 2019-07-31 15:18
 */

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
@RestController
@RequestMapping("/userInfoWebFlux")
public class HelloWebFluxController {

    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("Welcome to reactive world.");
    }

}