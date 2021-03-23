package com.example.TradeSysExchangeConnectivity.Exchange;

import com.example.TradeSysExchangeConnectivity.Config.Utility;
import com.example.TradeSysExchangeConnectivity.ExchangeModules.OrderBook;
import com.example.TradeSysExchangeConnectivity.ExchangeModules.PendingOrder;

import org.springframework.web.reactive.function.client.WebClient;
import redis.clients.jedis.Jedis;

public class ExchangeOne implements ApplicationRunner {
    Jedis jedis = new Jedis();

    @Override
    public void run() {
        while (true){
            String data = jedis.lpop("orderCreatedQ");
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
                pendingOrder.setExchange("exchange1");
            }
            jedis.lpush(orderBook.getId() + "orderbook",Utility.convertToString(pendingOrders));
        }
    }
}
