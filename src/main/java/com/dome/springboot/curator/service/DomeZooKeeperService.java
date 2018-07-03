package com.dome.springboot.curator.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class DomeZooKeeperService {

    Log log = LogFactory.getLog(DomeZooKeeperService.class);
    @Autowired
    CuratorFramework zkClient ;
    String lockPath = "/lock/order";

    public void makeOrderType(String type) {
        String path = lockPath+"/"+type;
        log.info("try do job for "+type+";time="+System.currentTimeMillis());
        try{
            InterProcessMutex lock = new InterProcessMutex(zkClient, path);
            if ( lock.acquire(10, TimeUnit.HOURS) ){
                try {

                    //模拟用时5秒
                    Thread.sleep(1000*5);
                    log.info("do job "+type+" done ;time="+System.currentTimeMillis());
                }
                finally{
                    lock.release();
                }

            }
        }catch(Exception ex){
            //zk异常
            ex.printStackTrace();
        }
    }

}
