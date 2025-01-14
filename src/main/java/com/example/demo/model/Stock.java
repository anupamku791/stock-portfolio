package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.annotation.Id;


@Entity
@Document(indexName = "stocks")
public class Stock {

    @Id
    private String stockId;
    private String name;
    private double currentPrice;
    private double openPrice;
    private double closePrice;
    private double highPrice;
    private double lowPrice;


    public Stock() {
    }

    public Stock(String stockId, String name, double currentPrice, double closePrice, double openPrice, double highPrice, double lowPrice) {
        this.stockId = stockId;
        this.name = name;
        this.currentPrice = currentPrice;
        this.closePrice = closePrice;
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }
}
