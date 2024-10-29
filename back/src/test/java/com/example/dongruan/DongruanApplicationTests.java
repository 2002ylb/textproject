package com.example.dongruan;

import com.example.dongruan.entity.TestRedis;
import com.example.dongruan.entity.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;

import java.util.Map;

@SpringBootTest

class DongruanApplicationTests {

    /*jedis快速入门*/
//    private Jedis jedis;
//
//    @BeforeEach
//    void setUp() {
//        jedis = new Jedis("192.168.234.129", 6379);
//         jedis.auth("5761665");
//         jedis.select(0);//默认是0
//    }
//
//    @Test
//    void contextLoads() {
//        String result = jedis.set("name","yang");
//        System.out.printf( result);
//        String name = jedis.get("name");
//        System.out.println(name);
//
//    }
//    @AfterEach
//    void tearDown(){
//        if(jedis!=null ){
//            jedis.close();
//        }
//    }

//springdataredis的快速入门

    @Resource
    private StringRedisTemplate stringRedisTemplate;

@Test
    void testspringdataredis() {
//    stringRedisTemplate.opsForValue().set("name","并不真正");
//    Object name = stringRedisTemplate.opsForValue().get("name");
//    System.out.println(name);

    stringRedisTemplate.opsForHash().put("dongruan:user", "address", "珠海");
    Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("dongruan:user");
    System.out.println(entries);
}
}
