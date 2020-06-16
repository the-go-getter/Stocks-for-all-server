package com.example.project.controllers;

import com.example.project.models.Stock;
import com.example.project.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",allowCredentials = "true",allowedHeaders = "*")
public class StockController {
    @Autowired
    StockService service;
    @GetMapping("/api/stocks")
    public List<Stock> findAllStocks(){return service.findAllStocks();}

    @GetMapping("/api/watchlists/{wid}/stocks")
    public List<Stock> findStocksForWatchlist(@PathVariable("wid") String wid){
        int wwid;
        try{
            wwid = Integer.parseInt(wid);
            return service.findStocksForWatchlist(wwid);
        }catch (NumberFormatException e){
            List<Stock> result = new ArrayList<>();
            return result;
        }
    }
    @GetMapping("/api/{category}/stocks")
    public List<Stock> findStocksByCategory(@PathVariable("category") String category){
        return service.findStocksByCategory(category);
    }
    @GetMapping("/api/stocks/{symbol}")
    public Stock findStockBySymbol(@PathVariable("symbol") String symbol){
        return service.findStockBySymbol(symbol);
    }
    @PostMapping("/api/stock")
    public Stock createStock(@RequestBody Stock stock){
        return service.createStock(stock);
    }
    @PostMapping("/api/watchlists/{wid}/stocks")
    public Stock createStockForWatchlist(@PathVariable("wid") String wid, @RequestBody Stock stock){
        int wwid;
        try{
            wwid = Integer.parseInt(wid);
            return service.createStockForWatchlist(wwid, stock);
        }catch (NumberFormatException e){
            return null;
        }
    }
    @DeleteMapping("/api/watchlists/{wid}/stocks/{sid}")
    public int deleteStockForWatchlist(@PathVariable("wid") String wid, @PathVariable("sid") String sid){
        int wwid;
        int ssid;
        try{
            wwid = Integer.parseInt(wid);
            ssid = Integer.parseInt(sid);
            return service.deleteStockForWatchlist(wwid, ssid);
        }catch (NumberFormatException e){
            return 0;
        }
    }
}
