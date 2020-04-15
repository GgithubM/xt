package com.jstar.xt.lock.redisson;

import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class TestRedissonCp {
    public static void main(String[] args) {
        Config config = new Config();
        //172.18.171.52:7000,172.18.171.53:7000,172.18.171.54:7000,172.18.171.52:7001,172.18.171.53:7001,172.18.171.54:7001

        config.useClusterServers()
                .setScanInterval(1000) // 集群状态扫描间隔时间，单位是毫秒
                //可以用"rediss://"来启用SSL连接
                .addNodeAddress("redis://172.18.171.57:7000")
                .addNodeAddress("redis://172.18.171.58:7000")
                .addNodeAddress("redis://172.18.171.59:7000")
                .addNodeAddress("redis://172.18.171.57:7001")
                .addNodeAddress("redis://172.18.171.58:7001")
                .addNodeAddress("redis://172.18.171.59:7001")
                .setPassword("xt_redis_xyz");

        RedissonClient redisson = Redisson.create(config);

        StringBuilder lockName=new StringBuilder();
        lockName.append("userId").append("tradeOrderNo").append("merchantFlag").append("merchantOrderTime");
        /*RLock lock = redisson.getLock(lockName.toString());
        lock.lock();

        lock.unlock();*/

        RMap<Object, Object> mapKey = redisson.getMap("mapKey");
        System.out.println("第一次输出的值："+mapKey.get("foo"));
        mapKey.put("foo","bar");
        mapKey = redisson.getMap("mapKey");
        System.out.println("第二次输出的值："+mapKey.get("foo"));
    }
}
