package com.dome.springboot.cache.controller;


import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;

@RestController
@Controller
@RequestMapping("/cache")
public class DomeCacheController {

    @Cacheable({"Menu","menuExt"})
    @RequestMapping("/get")
    public  HashMap<String,Object> getCache(){
        System.out.println("进行缓存");
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("datetime",new Date());

        return map;


    }

    @CachePut({"put"})
    @RequestMapping("/put")
    public HashMap<String,Object> cachePut(){
        System.out.println("更新缓存");

        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("datetime",new Date());

        return map;


    }

    @CacheEvict({"Menu"})
    @RequestMapping("/evict")
    public HashMap<String,Object> cacheEvict(){
        System.out.println("清除缓存");
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("datetime",new Date());

        return map;


    }

}
