package com.dome.springboot.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/redis")
@RestController
public class DomeRedisController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     *  key-value
     * @return
     */
    @RequestMapping("/value")
    public String setString(){

        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
//        redisTemplate.o
        stringStringValueOperations.set("testenv","redis");

        String testenv = stringStringValueOperations.get("testenv");
//        绑定key值对其进行操作
        redisTemplate.boundValueOps("testenv");
        return  testenv;
    }


    @RequestMapping("/pub")
    public String pub(){
        redisTemplate.convertAndSend("news1","hello word");
        return  "pub";
    }










}
