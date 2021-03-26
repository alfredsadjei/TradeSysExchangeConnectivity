package com.example.TradeSysExchangeConnectivity.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeOrder {
    private String orderId;
    private String productName;
    private double price;
    private int quantity;
    private String side;
    private String exchange;

    public ExchangeOrder(String orderId, String productName, double price, int quantity, String side) {
        this.orderId = orderId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.side = side;
    }

    public ExchangeOrder(){

    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String ticker) {
        this.productName = ticker;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public ExchangeOrder(String orderId, String productName, double price, int quantity, String side, String exchange) {
        this.orderId = orderId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.side = side;
        this.exchange = exchange;
    }
}
