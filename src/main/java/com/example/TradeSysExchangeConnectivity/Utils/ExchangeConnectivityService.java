package com.example.TradeSysExchangeConnectivity.Utils;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ExchangeConnectivityService {

    @GET("/3af0d829-27ac-4dc5-a605-feef97d4a072/order")
    Call<String> sendOrder(@Url String exchangeURL);
}
