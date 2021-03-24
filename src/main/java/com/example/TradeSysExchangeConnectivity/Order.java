package com.example.TradeSysExchangeConnectivity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    private String id;
    private String product;
    private int quantity;
    private double price;
    private String side;
}
