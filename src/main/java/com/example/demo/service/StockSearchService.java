package com.example.demo.service;

import com.example.demo.model.Stock;
import com.example.demo.repository.StockSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockSearchService {

    private final StockSearchRepository stockSearchRepository;

    @Autowired
    public StockSearchService(StockSearchRepository stockSearchRepository) {
        this.stockSearchRepository = stockSearchRepository;
    }

    public List<Stock> searchStocksByName(String name) {
        return stockSearchRepository.findByNameContaining(name);
    }
}

