package com.example.demo.controller;

import com.example.demo.model.Stock;
import com.example.demo.service.StockSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockSearchController {

    private final StockSearchService stockSearchService;

    @Autowired
    public StockSearchController(StockSearchService stockSearchService) {
        this.stockSearchService = stockSearchService;
    }

    @GetMapping("/search")
    public List<Stock> searchStocks(@RequestParam String query) {
        return stockSearchService.searchStocksByName(query);
    }
}
