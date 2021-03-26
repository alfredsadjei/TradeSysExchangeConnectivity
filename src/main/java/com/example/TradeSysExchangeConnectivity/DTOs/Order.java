package com.example.TradeSysExchangeConnectivity.DTOs;

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
    private String product;
    private int quantity;
    private double price;
    private String side;

}

/////