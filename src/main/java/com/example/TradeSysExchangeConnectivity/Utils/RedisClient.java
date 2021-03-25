package com.example.TradeSysExchangeConnectivity.Utils;

import com.example.TradeSysExchangeConnectivity.Utils.RedisServer;
import redis.clients.jedis.Jedis;

public class RedisClient {
    private static final Jedis jedis = new Jedis("redis-17849.c59.eu-west-1-2.ec2.cloud.redislabs.com",17849);

    public static Jedis connect() {
        jedis.auth(RedisServer.SERVER_KEY.getKeyVal());
        System.out.println("Connected");
        return jedis;
    }
}
