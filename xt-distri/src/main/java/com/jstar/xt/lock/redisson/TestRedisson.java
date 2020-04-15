package com.jstar.xt.lock.redisson;

import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class TestRedisson {
    public static void main(String[] args) {
        Config config = new Config();
        //172.18.169.52:7000,172.18.169.53:7000,172.18.169.54:7000,172.18.169.52:7001,172.18.169.53:7001,172.18.169.54:7001

        config.useClusterServers()
                .setScanInterval(1000) // 集群状态扫描间隔时间，单位是毫秒
                //可以用"rediss://"来启用SSL连接
                .addNodeAddress("redis://172.18.169.52:7000")
                .addNodeAddress("redis://172.18.169.53:7000")
                .addNodeAddress("redis://172.18.169.54:7000")
                .addNodeAddress("redis://172.18.169.52:7001")
                .addNodeAddress("redis://172.18.169.53:7001")
                .addNodeAddress("redis://172.18.169.54:7001")
                .setClientName("302012:moess-web")
                .setPassword("jc_redis_xyz");

        RedissonClient redisson = Redisson.create(config);

        StringBuilder lockName=new StringBuilder();
        lockName.append("userId").append("tradeOrderNo").append("merchantFlag").append("merchantOrderTime");
        RLock lock = redisson.getLock(lockName.toString());
        lock.lock();

        lock.unlock();

        RMap<Object, Object> mapKey = redisson.getMap("mapKey");
        System.out.println("第一次输出的值："+mapKey.get("302012:moess-web_foo"));
        mapKey.put("302012:moess-web_foo","bar");
        mapKey = redisson.getMap("mapKey");
        System.out.println("第二次输出的值："+mapKey.get("302012:moess-web_foo"));
        System.out.println("第3次输出的值："+mapKey.get("moess_xt_test_0001"));
    }
}
