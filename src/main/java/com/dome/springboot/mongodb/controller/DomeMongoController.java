package com.dome.springboot.mongodb.controller;


import com.dome.springboot.mongodb.bean.Baike;
import com.dome.springboot.mongodb.beanfactiory.BaikeFactory;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Controller
@RestController
@RequestMapping("/mongo")
public class DomeMongoController {
    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping("/get/{id}")
    public Baike getBaike(@PathVariable("id") String id){

        Baike baike = BaikeFactory.createBaike("getbaite");
//       会记录class的全类名 不能插入已存在的主键
//     主键重复报如下错误  E11000 duplicate key error collection: test.baike index: _id_ dup key: { : "savebaite" }
        mongoTemplate.insert(baike);

        return baike;
    }

    @RequestMapping("/save")
    public Baike saveBaike(String desc){

        Baike baike = BaikeFactory.createBaike("savebaite");
//       会记录class的全类名 以存在主键就修改
        baike.setDesc("desc:"+desc);

        mongoTemplate.save(baike);

        return baike;
    }


    @RequestMapping("/query")
    public List<Baike> queryBaike(String desc){
        Criteria gt = Criteria.where("comment.bad").gt(4);
        Query query = Query.query(gt);

        List<Baike> baikes = mongoTemplate.find(query, Baike.class);

        System.out.println(baikes);


        return baikes;
    }



    @RequestMapping("/update")
    public UpdateResult updateBaike(String desc){
        Criteria gt = Criteria.where("comment.bad").gt(10);
        Query query = Query.query(gt);

        Update update = new Update();
//        update.addToSet("desc","update val");

//        update.push("desc","update val");

        update.inc("comment.good",12);
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, Baike.class);

        System.out.println(updateResult);

        return updateResult;
    }



    /**
     * mongoDB的删除操作
     * @param desc
     * @return
     */
    @RequestMapping("/delete")
    public DeleteResult deleteBaike(String desc){
//        Bson where = Filters.where("");
        Criteria gt = Criteria.where("comment.bad").gt(10);
        Query query = Query.query(gt);

        List<Baike> baikes = mongoTemplate.find(query, Baike.class);

        DeleteResult remove = mongoTemplate.remove(query, Baike.class);
//        mongoTemplate.findAllAndRemove()
        System.out.println(remove);

        System.out.println(baikes);


        return remove;
    }
}
