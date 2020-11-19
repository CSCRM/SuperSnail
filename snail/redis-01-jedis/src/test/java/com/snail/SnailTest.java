package com.snail;


import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

public class SnailTest {

    @Test
    public void test(){
        Jedis jedis = new Jedis("192.168.150.128",6379);
        System.out.println("测试是否链接成功："+jedis.ping());
        System.out.println("清空数据："+jedis.flushDB());
        System.out.println("判断某个键是否存在："+jedis.exists("key"));
        System.out.println("set："+jedis.set("k1","v1"));
        System.out.println("set："+jedis.set("k2","v2"));
        System.out.println("所有键：");
        Set<String> set = jedis.keys("*");
        System.out.println(set);
        System.out.println("先删除键："+jedis.del("k1"));
        System.out.println("判断k1是否存在："+jedis.exists("k1"));
        System.out.println("查看k2的类型："+jedis.type("k2"));
        System.out.println("随机返回一个key："+jedis.randomKey());
        System.out.println("重命名："+jedis.rename("k2","key2"));
        System.out.println("get："+jedis.get("key1"));
        System.out.println("按索引查询："+jedis.select(0));
        System.out.println("当前数据库所有key："+jedis.dbSize());
        jedis.flushAll();
    }
}
