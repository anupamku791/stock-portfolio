package com.example.demo.repository;

import com.example.demo.model.Stock;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockSearchRepository extends ElasticsearchRepository<Stock, String> {
    List<Stock> findByNameContaining(String name);

}
