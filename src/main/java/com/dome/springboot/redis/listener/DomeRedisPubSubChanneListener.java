package com.dome.springboot.redis.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.io.UnsupportedEncodingException;

public class DomeRedisPubSubChanneListener implements MessageListener {


    @Override
    public void onMessage(Message message, byte[] bytes) {

        byte[] channel = message.getChannel();
        byte[] body = message.getBody();

        try {
            String content = new String(body,"UTF-8");
            String channelstr = new String(channel,"UTF-8");

            System.out.println("获取订阅信息get:"+content+" from "+channelstr);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }
}
