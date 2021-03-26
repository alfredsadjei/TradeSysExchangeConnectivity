package com.example.TradeSysExchangeConnectivity;


import com.example.TradeSysExchangeConnectivity.DTOs.Order;
import com.example.TradeSysExchangeConnectivity.Utils.ExchangeConnectivityService;
import com.example.TradeSysExchangeConnectivity.DTOs.ExchangeOrder;
import com.example.TradeSysExchangeConnectivity.Utils.RedisClient;
import lombok.SneakyThrows;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Component
public class MakeOrder implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {

        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        //creating a jedis instance
        Jedis jedis = RedisClient.connect();

        Runnable queuePopper = new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                //pop from queue only if there is something to pop
                if (jedis.lindex("orderCreatedQ",0) != null){

                    String data = jedis.lpop("orderCreatedQ");

                    System.out.println(data);

                    //convert to POJO
                    ExchangeOrder exOrder = Utility.convertToObject(data, ExchangeOrder.class);

                    Order strippedExOrder = this.strip(exOrder);

                    String orderId;
                    Call<String> getOrderID;

                    if(exOrder.getExchange().equalsIgnoreCase("ex1")){
                        //retrofit web client
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("https://exchange.matraining.com")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        ExchangeConnectivityService ecService = retrofit.create(ExchangeConnectivityService.class);

                        getOrderID = ecService.sendOrder(strippedExOrder);
                    }else {

                        //retrofit web client
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("https://exchange2.matraining.com")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        ExchangeConnectivityService ecService = retrofit.create(ExchangeConnectivityService.class);

                        getOrderID = ecService.sendOrder(strippedExOrder);
                    }

                    orderId = getOrderID.execute().body();

                    System.out.println("Order Successful: " + orderId);
                }
            }

            private Order strip(ExchangeOrder exOrder) {
                return new Order(exOrder.getTicker(),  exOrder.getQuantity(),exOrder.getPrice(), exOrder.getSide());
            }
        };

        final ScheduledFuture<?> queueHandler = scheduler.scheduleWithFixedDelay(queuePopper,1,1, TimeUnit.SECONDS);

    }

}

