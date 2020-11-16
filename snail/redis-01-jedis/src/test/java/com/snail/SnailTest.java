package com.snail;


import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.Set;

public class SnailTest {

    @Test
    public void test(){
        Jedis jedis = new Jedis("192.168.35.128",6379);//192.168.35.128 192.168.150.128
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

    @Test
    public void testTX(){
        Jedis jedis = new Jedis("192.168.35.128",6379);
        jedis.flushDB();
        //开启事务
        Transaction multi = jedis.multi();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("j1","o1");
        jsonObject.put("j2","o2");
        String s = jsonObject.toJSONString();

        try {
            multi.set("k1",s);
            multi.set("k2",s);
            multi.exec();
        } catch (Exception e) {
            multi.discard();//放弃事务
            e.printStackTrace();
        }finally {
            System.out.println(jedis.get("k1"));
            System.out.println(jedis.get("k2"));
            jedis.close();//关闭连接
        }


    }
}
