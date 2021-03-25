package com.example.TradeSysExchangeConnectivity.Utils;

import com.example.TradeSysExchangeConnectivity.DTOs.Order;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ExchangeConnectivityService {

    @POST("/3af0d829-27ac-4dc5-a605-feef97d4a072/order")
    Call<String> sendOrder(@Body Order strippedExOrder);
}
