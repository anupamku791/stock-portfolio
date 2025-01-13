package com.example.demo.service;
import com.example.demo.dto.PortfolioResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PortfolioService {

    private final ConcurrentHashMap<String, List<PortfolioResponse.Holding>> userPortfolios = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Double> userProfits = new ConcurrentHashMap<>();

    public PortfolioResponse getPortfolio(String userId) {
        List<PortfolioResponse.Holding> holdings = userPortfolios.getOrDefault(userId, new ArrayList<>());

        double totalBuyPrice = holdings.stream().mapToDouble(h -> h.getBuyPrice() * h.getQuantity()).sum();
        double totalPortfolioHolding = holdings.stream().mapToDouble(h -> h.getCurrentPrice() * h.getQuantity()).sum();
        double totalPL = userProfits.getOrDefault(userId, 0.0);
        double totalPLPercentage = totalBuyPrice > 0 ? (totalPL / totalBuyPrice) * 100 : 0;

        PortfolioResponse response = new PortfolioResponse();
        response.setHoldings(holdings);
        response.setTotalBuyPrice(totalBuyPrice);
        response.setTotalPortfolioHolding(totalPortfolioHolding);
        response.setTotalPL(totalPL);
        response.setTotalPLPercentage(totalPLPercentage);

        return response;
    }

    public void updatePortfolio(String userId, String stockId, int quantity, double tradePrice, boolean isBuy) {
        userPortfolios.putIfAbsent(userId, new ArrayList<>());
        userProfits.putIfAbsent(userId, 0.0);
        List<PortfolioResponse.Holding> holdings = userPortfolios.get(userId);

        PortfolioResponse.Holding existingHolding = holdings.stream()
                .filter(h -> h.getStockId().equals(stockId))
                .findFirst()
                .orElse(null);

        if (isBuy) {
            if (existingHolding == null) {
                PortfolioResponse.Holding newHolding = new PortfolioResponse.Holding();
                newHolding.setStockId(stockId);
                newHolding.setStockName("Stock " + stockId); // Simulated stock name
                newHolding.setQuantity(quantity);
                newHolding.setBuyPrice(tradePrice);
                newHolding.setCurrentPrice(tradePrice); // Assume current price equals trade price initially
                holdings.add(newHolding);
            } else {
                int totalQuantity = existingHolding.getQuantity() + quantity;
                double newBuyPrice = ((existingHolding.getBuyPrice() * existingHolding.getQuantity()) + (tradePrice * quantity)) / totalQuantity;
                existingHolding.setQuantity(totalQuantity);
                existingHolding.setBuyPrice(newBuyPrice);
            }
        } else { // Sell logic
            if (existingHolding == null || existingHolding.getQuantity() < quantity) {
                throw new IllegalArgumentException("Insufficient quantity to sell.");
            }

            int remainingQuantity = existingHolding.getQuantity() - quantity;
            double profit = quantity * (tradePrice - existingHolding.getBuyPrice());
            userProfits.put(userId, userProfits.get(userId) + profit);

            if (remainingQuantity == 0) {
                holdings.remove(existingHolding);
            } else {
                existingHolding.setQuantity(remainingQuantity);
            }
        }
    }
}
