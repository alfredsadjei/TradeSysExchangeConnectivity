package com.example.TradeSysExchangeConnectivity.Exchange;

import com.example.TradeSysExchangeConnectivity.Config.Utility;
import com.example.TradeSysExchangeConnectivity.ExchangeConnectivityRedisClient;
import com.example.TradeSysExchangeConnectivity.ExchangeModules.OrderBook;
import com.example.TradeSysExchangeConnectivity.ExchangeModules.PendingOrder;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.web.reactive.function.client.WebClient;
import redis.clients.jedis.Jedis;

public class ExchangeOne implements ApplicationRunner {



    @Override
    public void run(ApplicationArguments args) throws Exception {
        Jedis jedis = ExchangeConnectivityRedisClient.connect();
        while (true){
            String data = jedis.lpop("orderCreated");
            if(data == null) continue;
            System.out.println(data);
            OrderBook orderBook = Utility.convertToObject(data,OrderBook.class);

            PendingOrder[] response = null;
            String product = orderBook.getProduct();

            String side = orderBook.getSide().toLowerCase();

            if (side.equals("sell")){
                response = WebClient.builder()
                        .baseUrl("https://exchange.matraining.com")
                        .build()
                        .get()
                        .uri("/orderbook"+"/"+product+"/"+"buy")
                        .retrieve()
                        .bodyToMono(PendingOrder[].class)
                        .block();
            }else {
                response = WebClient.builder()
                        .baseUrl("https://exchange.matraining.com")
                        .build()
                        .get()
                        .uri("/orderbook"+"/"+product+"/"+"sell")
                        .retrieve()
                        .bodyToMono(PendingOrder[].class)
                        .block();
            }

            String result = Utility.convertToString(response);
            PendingOrder[] pendingOrders = Utility.convertToObject(result,PendingOrder[].class);

            if(pendingOrders == null)
                System.out.println("Pending orders null");
            for (PendingOrder pendingOrder : pendingOrders) {
                pendingOrder.setExchange("pending");
            }
            jedis.lpush(orderBook.getId() + "orderbook",Utility.convertToString(pendingOrders));
        }
    }
}
