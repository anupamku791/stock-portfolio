package com.example.demo.consumer;

import com.example.demo.dto.TradeRequest;
import com.example.demo.model.Trade;
import com.example.demo.repository.PortfolioRepository;
import com.example.demo.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TradeConsumer {

    private final PortfolioRepository portfolioRepository;
    private final StockRepository stockRepository;

    @Autowired
    public TradeConsumer(PortfolioRepository portfolioRepository, StockRepository stockRepository) {
        this.portfolioRepository = portfolioRepository;
        this.stockRepository = stockRepository;
    }

    @KafkaListener(topics = "trade_requests", groupId = "trade_group")
    public void processTrade(TradeRequest tradeRequest) {
        // Map TradeRequest to Trade model
        Trade trade = mapToTrade(tradeRequest);

        if ("BUY".equalsIgnoreCase(trade.getTradeType())) {
            processBuy(trade);
        } else if ("SELL".equalsIgnoreCase(trade.getTradeType())) {
            processSell(trade);
        }
    }

    private Trade mapToTrade(TradeRequest tradeRequest) {
        Trade trade = new Trade();
        trade.setUserAccountId(tradeRequest.getUserAccountId());
        trade.setStockId(tradeRequest.getStockId());
        trade.setQuantity(tradeRequest.getQuantity());
        trade.setTradeType(tradeRequest.getTradeType());
        return trade;
    }

    private void processBuy(Trade trade) {
        // Implement the buy logic here (update portfolio, stock quantities, etc.)
    }

    private void processSell(Trade trade) {
        // Implement the sell logic here (update portfolio, calculate P/L, etc.)
    }
}
