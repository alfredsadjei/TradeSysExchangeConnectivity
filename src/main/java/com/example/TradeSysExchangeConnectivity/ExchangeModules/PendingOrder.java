package com.example.TradeSysExchangeConnectivity.ExchangeModules;

public class PendingOrder {
    private String product;
    private int quantity;
    private double price;
    private String side;
    private int cumulativeQuantity;
    private String exchange;

    public PendingOrder() {
    }

    public PendingOrder(String product, int quantity, double price, String side, int cumulativeQuantity, String exchange) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
        this.cumulativeQuantity = cumulativeQuantity;
        this.exchange = exchange;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public int getCumulativeQuantity() {
        return cumulativeQuantity;
    }

    public void setCumulativeQuantity(int cumulativeQuantity) {
        this.cumulativeQuantity = cumulativeQuantity;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    @Override
    public String toString() {
        return "PendingOrder{" +
                "product='" + product + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", side='" + side + '\'' +
                ", cumulativeQuantity=" + cumulativeQuantity +
                ", exchange='" + exchange + '\'' +
                '}';
    }
}
