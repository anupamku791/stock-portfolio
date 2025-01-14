package com.example.demo.model;

import jakarta.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    @ElementCollection
    @CollectionTable(name = "portfolio_holdings", joinColumns = @JoinColumn(name = "portfolio_id"))
    @MapKeyColumn(name = "stock_id")
    @Column(name = "quantity")
    private Map<String, Integer> holdings = new HashMap<>();

    private double totalBuyPrice;
    private double totalPortfolioValue;
    private double totalProfitLoss;

    public Portfolio() {
    }

    public Portfolio(String userId) {
        this.userId = userId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, Integer> getHoldings() {
        return holdings;
    }

    public void setHoldings(Map<String, Integer> holdings) {
        this.holdings = holdings;
    }

    public double getTotalBuyPrice() {
        return totalBuyPrice;
    }

    public void setTotalBuyPrice(double totalBuyPrice) {
        this.totalBuyPrice = totalBuyPrice;
    }

    public double getTotalPortfolioValue() {
        return totalPortfolioValue;
    }

    public void setTotalPortfolioValue(double totalPortfolioValue) {
        this.totalPortfolioValue = totalPortfolioValue;
    }

    public double getTotalProfitLoss() {
        return totalProfitLoss;
    }

    public void setTotalProfitLoss(double totalProfitLoss) {
        this.totalProfitLoss = totalProfitLoss;
    }
}
