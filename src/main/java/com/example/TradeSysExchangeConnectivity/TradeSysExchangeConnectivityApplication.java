package com.example.TradeSysExchangeConnectivity;

import com.example.TradeSysExchangeConnectivity.Exchange.ExchangeOne;
import com.example.TradeSysExchangeConnectivity.Exchange.MakeOrder;
import com.example.TradeSysExchangeConnectivity.ExchangeModules.Order;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootApplication
@Configuration
public class TradeSysExchangeConnectivityApplication {




	public static void main(String[] args) {
		SpringApplication.run(TradeSysExchangeConnectivityApplication.class, args);

		ExchangeOne exchange = new ExchangeOne();

		Thread exchangeThread = new Thread(exchange);
		exchangeThread.start();

		MakeOrder makeOrder = new MakeOrder();
		Thread makeOrderThread = new Thread(makeOrder);
		makeOrderThread.start();
	}

}
