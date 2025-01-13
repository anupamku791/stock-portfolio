package com.example.demo.controller;


import com.example.demo.service.UploadStockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/stocks/upload")
public class StockController {
    private final UploadStockService uploadStockService;

    public StockController(UploadStockService uploadStockService){
        this.uploadStockService = uploadStockService;
    }

    @PostMapping
    public ResponseEntity<String> uploadStockFile(@RequestParam("file") MultipartFile file) {
        String response = uploadStockService.processStockFile(file);
        return ResponseEntity.ok(response);
    }
}
