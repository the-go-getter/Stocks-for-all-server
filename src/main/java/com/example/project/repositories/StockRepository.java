package com.example.project.repositories;

import com.example.project.models.Stock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, Integer> {

    @Query("SELECT s FROM Stock s")
    public List<Stock> findAllStocks();

    @Query("SELECT s FROM Stock s WHERE s.id=:sid")
    public Stock findStockById(@Param("sid") Integer sid);

    @Query("SELECT s FROM Stock s WHERE s.category=:category")
    public List<Stock> findStocksByCategory(@Param("category") String category);

    @Query("SELECT s FROM Stock s WHERE s.symbol=:symbol")
    public Stock findStockBySymbol(@Param("symbol") String symbol);

}
