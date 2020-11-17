package com.snail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snail.pojo.User;
import com.snail.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Redis02SpringbootApplicationTests {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
        // 在企业开发中，我们80%的情况下，都不会使用这个原生的方式去编写代码
        // RedisUtil

        //opsForValue  操作字符串
        //opsFor...  操作什么。。。

        //常用的方法可以直接通过redisTemplate操作

        //获取redis的连接对象
        //RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        //connection.flushDb();

//        redisTemplate.opsForValue().set("k1","v1");
//        redisTemplate.opsForValue().set("k2","v2");
//        System.out.println(redisTemplate.opsForValue().get("k1"));

    }

    @Test
    void testUser() throws JsonProcessingException {
        //真实开发一般都是使用json来转递对象
        User user = new User("涡流",21);
        //序列化
        String s = new ObjectMapper().writeValueAsString(user);
        redisTemplate.opsForValue().set("user",user);
        System.out.println(redisTemplate.opsForValue().get("user"));


//        redisUtil.set("k1", "v1");
//        redisUtil.set("k2", "v2");
//        redisUtil.set("k3", "v3");
//        redisUtil.del("k1","k2","k3");
    }
}
