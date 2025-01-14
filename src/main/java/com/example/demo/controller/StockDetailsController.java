package com.example.demo.controller;

import com.example.demo.model.Stock;
import com.example.demo.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/stocks/details")
public class StockDetailsController {

    private final StockRepository stockRepository;

    @Autowired
    public StockDetailsController(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @GetMapping
    public ResponseEntity<?> getStockDetails(@RequestParam("stockId") String stockId) {
        Optional<Stock> stockOptional = stockRepository.findById(stockId);

        if (stockOptional.isPresent()) {
            Stock stock = stockOptional.get();
            return ResponseEntity.ok(stock);
        } else {
            return ResponseEntity.status(404).body("Stock not found for ID: " + stockId);
        }
    }
}
