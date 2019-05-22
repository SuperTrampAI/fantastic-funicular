package com.github.supertrampai.fantasticfunicular.redis;

import com.github.supertrampai.fantasticfunicular.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() throws Exception {
        /*stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));*/
        // 保存对象
        User user = new User("1",1);
        redisTemplate.opsForValue().set(user.getName(), user);

        user = new User("2",11);
        redisTemplate.opsForValue().set(user.getName(), user);

        user = new User("3",12);
        redisTemplate.opsForValue().set(user.getName(), user);

        Assert.assertEquals(user, redisTemplate.opsForValue().get("2").getClass());
        Assert.assertEquals(user, redisTemplate.opsForValue().get("1").getClass());


    }

    @Test
    public void testObj() throws Exception {
        User user=new User("aa@126.com", "aa", 56, 1,"123",12);
        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        operations.set("com.neox", user);
        operations.set("com.github.supertrampai.fantasticfunicular", user,1, TimeUnit.SECONDS);
        Thread.sleep(1000);
        //redisTemplate.delete("com.neo.f");
        boolean exists=redisTemplate.hasKey("com.neo.f");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
        // Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
    }
}
