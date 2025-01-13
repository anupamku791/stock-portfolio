package com.example.demo.dto;
import java.util.List;

public class PortfolioResponse {
    private List<Holding> holdings;
    private double totalPortfolioHolding;
    private double totalBuyPrice;
    private double totalPL;
    private double totalPLPercentage;

    public List<Holding> getHoldings() {
        return holdings;
    }

    public void setHoldings(List<Holding> holdings) {
        this.holdings = holdings;
    }

    public double getTotalPortfolioHolding() {
        return totalPortfolioHolding;
    }

    public void setTotalPortfolioHolding(double totalPortfolioHolding) {
        this.totalPortfolioHolding = totalPortfolioHolding;
    }

    public double getTotalBuyPrice() {
        return totalBuyPrice;
    }

    public void setTotalBuyPrice(double totalBuyPrice) {
        this.totalBuyPrice = totalBuyPrice;
    }

    public double getTotalPL() {
        return totalPL;
    }

    public void setTotalPL(double totalPL) {
        this.totalPL = totalPL;
    }

    public double getTotalPLPercentage() {
        return totalPLPercentage;
    }

    public void setTotalPLPercentage(double totalPLPercentage) {
        this.totalPLPercentage = totalPLPercentage;
    }

    public static class Holding {
        private String stockName;
        private String stockId;
        private int quantity;
        private double buyPrice;
        private double currentPrice;
        private double gainLoss;

        public String getStockName() {
            return stockName;
        }

        public void setStockName(String stockName) {
            this.stockName = stockName;
        }

        public String getStockId() {
            return stockId;
        }

        public void setStockId(String stockId) {
            this.stockId = stockId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getBuyPrice() {
            return buyPrice;
        }

        public void setBuyPrice(double buyPrice) {
            this.buyPrice = buyPrice;
        }

        public double getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(double currentPrice) {
            this.currentPrice = currentPrice;
        }

        public double getGainLoss() {
            return gainLoss;
        }

        public void setGainLoss(double gainLoss) {
            this.gainLoss = gainLoss;
        }
    }
}