package com.example.demo.service;
import com.example.demo.model.Stock;
import com.example.demo.repository.StockRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class UploadStockService {

    private final StockRepository stockRepository;

    @Autowired
    public UploadStockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public String processStockFile(MultipartFile file) {
        if (file.isEmpty()) {
            return "File is empty. Please upload a valid CSV file.";
        }

        List<Stock> stocks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {

            for (CSVRecord record : csvParser) {
                Stock stock = new Stock();
                stock.setStockId(record.get("Stock ID"));
                stock.setName(record.get("Stock Name"));
                stock.setCurrentPrice(Double.parseDouble(record.get("Current Price")));

                // Add the stock to the list for batch saving
                stocks.add(stock);
            }

            // Save all stocks to the repository
            stockRepository.saveAll(stocks);
            return "Stocks uploaded and updated successfully.";

        } catch (IOException e) {
            return "An error occurred while processing the file: " + e.getMessage();
        } catch (Exception e) {
            return "Unexpected error: " + e.getMessage();
        }
    }
}

