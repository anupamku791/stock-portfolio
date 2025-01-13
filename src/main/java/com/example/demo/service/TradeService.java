package com.example.demo.service;

import com.example.demo.dto.TradeRequest;
import com.example.demo.dto.TradeResponse;
import org.springframework.stereotype.Service;

@Service
public class TradeService {

    private final PortfolioService portfolioService;

    public TradeService(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    public TradeResponse processTrade(TradeRequest request) {
        if (request.getQuantity() <= 0) {
            return new TradeResponse("Failure", "Quantity must be greater than zero.");
        }

        if (!"Buy".equalsIgnoreCase(request.getTradeType()) && !"Sell".equalsIgnoreCase(request.getTradeType())) {
            return new TradeResponse("Failure", "Invalid trade type. Must be 'Buy' or 'Sell'.");
        }

        if (request.getStockId() == null || request.getStockId().isEmpty()) {
            return new TradeResponse("Failure", "Stock ID cannot be null or empty.");
        }

        try {
            if ("Buy".equalsIgnoreCase(request.getTradeType())) {
                portfolioService.updatePortfolio(request.getUserAccountId(), request.getStockId(), request.getQuantity(), request.getTradePrice(), true);
            } else {
                portfolioService.updatePortfolio(request.getUserAccountId(), request.getStockId(), request.getQuantity(), request.getTradePrice(), false);
            }
        } catch (IllegalArgumentException e) {
            return new TradeResponse("Failure", e.getMessage());
        }

        return new TradeResponse("Success", "Trade processed successfully.");
    }
}