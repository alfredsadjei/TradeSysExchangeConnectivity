package com.example.TradeSysExchangeConnectivity;


import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import redis.clients.jedis.Jedis;

@Component
public class MakeOrder implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String key = "3af0d829-27ac-4dc5-a605-feef97d4a072";
        WebClient webClient = WebClient.create("https://exchange.matraining.com");
        Jedis jedis = RedisClient.connect();
        while (true) {
            String data = jedis.lpop("orderCreatedQ");

            System.out.println(data);

            Order order = Utility.convertToObject(data, Order.class);
            String orderId = webClient.post()
                    .uri("/" + key + "/order")
                    .body(Mono.just(order), Order.class)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            System.out.println(data);
            System.out.println("Order Successful: " + orderId);
//            jedis.del("data");
        }
    }
}

