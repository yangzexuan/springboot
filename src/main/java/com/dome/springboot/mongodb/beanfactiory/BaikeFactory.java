package com.dome.springboot.mongodb.beanfactiory;


import com.dome.springboot.mongodb.bean.Baike;
import com.dome.springboot.mongodb.bean.Comment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class BaikeFactory {

    private static Random random = new Random();
    public static Baike createBaike(String id){
        Baike baike = new Baike();
        baike.setId(id);
        baike.setCrateDate(new Date());
        baike.setDesc("这个是中文");
        List<String> list = new ArrayList<>(2);
        list.add("tag-1");
        list.add("tag-2");
        baike.setTag(list);
        Comment comment = new Comment();

        comment.setBad(random.nextInt(15));
        comment.setGood(random.nextInt(40));

        baike.setComment(comment);

        return baike;
    }
}
