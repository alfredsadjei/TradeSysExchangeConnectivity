package com.example.TradeSysExchangeConnectivity.ExchangeModules;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderBook {
    private String id;
    private String product;
    private String side;

}
