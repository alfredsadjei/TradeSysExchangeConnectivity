package com.example.TradeSysExchangeConnectivity.Config;

import com.example.TradeSysExchangeConnectivity.ExchangeConnectivityRedisClient;
import org.springframework.context.annotation.Bean;

public class RedisConfiguration {
    @Bean
    public ExchangeConnectivityRedisClient redisClientFactory(){
        return new ExchangeConnectivityRedisClient();
    }
}
