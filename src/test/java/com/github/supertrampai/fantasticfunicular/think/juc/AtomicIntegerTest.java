package com.github.supertrampai.fantasticfunicular.think.juc;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @version V1.0
 * @program: fantastic-funicular
 * @description: TODO
 * @author: lxh800109@gmail.com
 * @create: 2019-05-30 15:27
 * 修改历史：
 * 时间           作者          版本        描述
 * ====================================================
 **/
public class AtomicIntegerTest {
    @Test
    public void testAll() throws InterruptedException{
        final AtomicInteger value = new AtomicInteger(10);
        assertEquals(value.compareAndSet(1, 2), false);
        assertEquals(value.get(), 10);
        assertTrue(value.compareAndSet(10, 3));
        assertEquals(value.get(), 3);
        value.set(0);
        //
        assertEquals(value.incrementAndGet(), 1);
        assertEquals(value.getAndAdd(2),1);
        assertEquals(value.getAndSet(5),3);
        assertEquals(value.get(),5);
        //
        final int threadSize = 10;
        Thread[] ts = new Thread[threadSize];
        for (int i = 0; i < threadSize; i++) {
            ts[i] = new Thread() {
                public void run() {
                    value.incrementAndGet();
                }
            };
        }
        //
        for(Thread t:ts) {
            t.start();
        }
        for(Thread t:ts) {
            t.join();
        }
        //
        assertEquals(value.get(), 5+threadSize);
    }
}
