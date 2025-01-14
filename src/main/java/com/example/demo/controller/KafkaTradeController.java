package com.example.demo.consumer;

import com.example.demo.dto.TradeRequest;
import com.example.demo.model.Trade;
import com.example.demo.repository.PortfolioRepository;
import com.example.demo.repository.StockRepository;
import com.example.demo.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TradeConsumer {

    private final PortfolioRepository portfolioRepository;
    private final StockRepository stockRepository;
    private final TradeRepository tradeRepository;

    @Autowired
    public TradeConsumer(PortfolioRepository portfolioRepository, StockRepository stockRepository, TradeRepository tradeRepository) {
        this.portfolioRepository = portfolioRepository;
        this.stockRepository = stockRepository;
        this.tradeRepository = tradeRepository;
    }

    @KafkaListener(topics = "trade_requests", groupId = "trade_group")
    public void processTrade(TradeRequest tradeRequest) {
        Trade trade = mapToTrade(tradeRequest);

        if ("BUY".equalsIgnoreCase(trade.getTradeType())) {
            processBuy(trade);
        } else if ("SELL".equalsIgnoreCase(trade.getTradeType())) {
            processSell(trade);
        }

        // Save the trade record in the database
        tradeRepository.save(trade);
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
        // Implement portfolio update logic for BUY
    }

    private void processSell(Trade trade) {
        // Implement portfolio update logic for SELL
    }
}
