package com.example.TradeSysExchangeConnectivity.Exchange;


import com.example.TradeSysExchangeConnectivity.Config.Utility;
import com.example.TradeSysExchangeConnectivity.ExchangeModules.Order;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import redis.clients.jedis.Jedis;


public  class MakeOrder implements Runnable {
    Jedis jedis = new Jedis();

    String key = "3af0d829-27ac-4dc5-a605-feef97d4a072";
    WebClient webClient = WebClient.create("https://exchange.matraining.com");

    @Override
    public void run() {
        while (true){

            String data = jedis.lpop("orderCreatedQ");
            if(data == null) continue;

            String order2 = Utility.convertToString(data);
            System.out.println(order2);

            Order order = Utility.convertToObject(data, Order.class);
            String orderId = webClient.post().uri("/"+key+"/order")
                    .body(Mono.just(order), Order.class)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            System.out.println(data);
            System.out.println("Order placed successfully, orderId: " +orderId);
            jedis.del("data");
        }
    }
}
