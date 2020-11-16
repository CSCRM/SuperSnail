package com.snail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Redis02SpringbootApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    void contextLoads() {
        //opsForValue  操作字符串
        //opsFor...  操作什么。。。

        //常用的方法可以直接通过redisTemplate操作

        //获取redis的连接对象
        //RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        //connection.flushDb();

        redisTemplate.opsForValue().set("k1","v1");
        redisTemplate.opsForValue().set("k2","v2");
        System.out.println(redisTemplate.opsForValue().get("k1"));

    }

}
