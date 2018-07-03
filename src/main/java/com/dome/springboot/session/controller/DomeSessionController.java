package com.dome.springboot.session.controller;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@Controller
@RequestMapping("/session")
public class DomeSessionController {
    private Log log = LogFactory.getLog(DomeSessionController.class);

    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping("/msg")
    public HashMap<String,Object> getSessionMsg(HttpSession session , HttpServletRequest request){
        HttpSession session1 = request.getSession();


        HashMap<String,Object> map = new HashMap<>();
        map.put("session",session.getClass());
        map.put("session1",session1.getClass());
        map.put("sessioneq",session1==session);

        log.info(map);
        String name = "xiandafu";
        session.setAttribute("user", name);
        redisTemplate.opsForValue().set("why", "ccccc");

         

        return map;
    }

}
