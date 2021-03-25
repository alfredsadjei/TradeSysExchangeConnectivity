package com.example.TradeSysExchangeConnectivity;


import com.example.TradeSysExchangeConnectivity.Utils.ExchangeConnectivityService;
import com.example.TradeSysExchangeConnectivity.DTOs.ExchangeOrder;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Component
public class MakeOrder implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {


        //String key = "3af0d829-27ac-4dc5-a605-feef97d4a072";

        //WebClient webClient = WebClient.create("https://exchange.matraining.com");

        //retrofit web client
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://exchange.matraining.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ExchangeConnectivityService ecService = retrofit.create(ExchangeConnectivityService.class);

        Jedis jedis = RedisClient.connect();

        while (true) {
            //pop from queue only if there is something to pop
            if (jedis.lindex("orderCreatedQ",0) != null){

                String data = jedis.lpop("orderCreatedQ");
                System.out.println(data);

                //convert to POJO
                ExchangeOrder order = Utility.convertToObject(data, ExchangeOrder.class);

                String orderId;
                Call<String> getOrderID;

//            if(order.getExchange().equalsIgnoreCase("ex1")){
//                getOrderID = ecService.sendOrder("https://exchange.matraining.com");
//            }else {
//                getOrderID = ecService.sendOrder("https://exchange2.matraining.com");
//            }
//            orderId = getOrderID.execute().body();

                //System.out.println("Order Successful: " + orderId);
            }


        }
    }
}

