package com.dome.springboot.mybatis.controller;

import com.dome.springboot.mybatis.service.DomeMybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mybaits")
@RestController
public class DomeMyBatisController {

    @Autowired
    private DomeMybatisService service;


    @RequestMapping("/get")
    public Object getDate(){

        List<Map> query = this.service.query();
        return query;
    }
}
