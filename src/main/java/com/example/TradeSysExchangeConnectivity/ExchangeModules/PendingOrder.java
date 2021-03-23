package com.example.TradeSysExchangeConnectivity.ExchangeModules;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PendingOrder {
    private String product;
    private int quantity;
    private double price;
    private String side;
    private int cumulativeQuantity;
    private String exchange;

}
