/*
 * Copyright (c) 2017- 2019 SuperTrampAI.github All Rights Reserved.
 */

package com.github.supertrampai.fantasticfunicular.action;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * @Description
 * @auther lxh800109@gmail.com
 * @create 2019-07-31 15:21
 */
@RunWith(SpringRunner.class)
@WebFluxTest(controllers = HelloController.class)
public class HelloWebFluxTest {
    @Autowired
    WebTestClient client;

    @Test
    public void getHello() {
        client.get().uri("/userInfoWebFlux/hello").exchange().expectStatus().isOk();
    }
}