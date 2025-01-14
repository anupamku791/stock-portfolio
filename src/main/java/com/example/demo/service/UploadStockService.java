package com.example.demo.service;

import com.example.demo.model.Stock;
import com.example.demo.repository.StockRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class UploadStockService {

    private final StockRepository stockRepository;

    @Autowired
    public UploadStockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }
    @Scheduled(cron = "0 0 22 * * *")
    public String updateStocksFromURL(String fileUrl) {
        List<Stock> stocks = new ArrayList<>();

        try {
            // Download the file from the given URL
            URL url = new URL(fileUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                 CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

                for (CSVRecord record : csvParser) {
                    // Skip rows that do not have valid stock symbols
                    String symbol = record.get("SYMBOL ").trim();
                    if (symbol.isEmpty() || symbol.equalsIgnoreCase("NIFTY 50")) {
                        continue;
                    }

                    Stock stock = new Stock();
                    stock.setStockId(symbol);
                    stock.setName(symbol); // Assuming stock name and symbol are the same here
                    stock.setCurrentPrice(Double.parseDouble(record.get("LTP ").replace(",", "")));

                    // Add the stock to the list for batch saving
                    stocks.add(stock);
                }

                // Save all stocks to the repository
                stockRepository.saveAll(stocks);
                return "Stocks downloaded and updated successfully.";

            }
        } catch (Exception e) {
            return "An error occurred while updating stocks: " + e.getMessage();
        }
    }

    public String processStockFile(MultipartFile file) {
//        In case, you want to auto update the stock details.
        final String url = "https://www.nseindia.com/api/equity-stockIndices?csv=true&index=NIFTY%2050&selectValFormat=crores";
        return updateStocksFromURL(url);
    }
}
