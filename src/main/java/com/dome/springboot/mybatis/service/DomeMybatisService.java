package com.dome.springboot.mybatis.service;



import com.dome.springboot.mybatis.mapper.MyUserMapper;
import com.dome.springboot.mybatis.mapper.OtherUserMapper;
import com.dome.springboot.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class DomeMybatisService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MyUserMapper myUserMapper;

    @Autowired
    private OtherUserMapper otherUserMapper;


    @Transactional
    public List<Map> query(){
        String cuserid = "cc";

        Object map = this.userMapper.selectUser(cuserid);
        System.out.println(map);

        System.out.println("处理");
        List<Map> map1 = this.myUserMapper.queryByCuserid(cuserid);
        System.out.println(map1);

        List<Map> maps = this.otherUserMapper.selectUser(cuserid);
        System.out.println("otherUserMapper的处理:"+maps);

        return map1;





    }
}
