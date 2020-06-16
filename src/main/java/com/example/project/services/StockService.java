package com.example.project.services;

import com.example.project.models.Stock;
import com.example.project.models.Watchlist;
import com.example.project.repositories.StockRepository;
import com.example.project.repositories.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private WatchlistRepository watchlistRepository;

    public List<Stock> findAllStocks(){
        return stockRepository.findAllStocks();
    }


    public List<Stock> findStocksForWatchlist(Integer wid){
        Watchlist tempwl = watchlistRepository.findWatchlistById(wid);
        if(tempwl==null){
            return null;
        }else{
            return tempwl.getStocks();
        }
    }

    public Stock findStockBySymbol(String symbol){
        return stockRepository.findStockBySymbol(symbol);
    }

    public List<Stock> findStocksByCategory(String category){
        return stockRepository.findStocksByCategory(category);
    }

    public Stock createStock(Stock stock){
        String symbol = stock.getSymbol();
        if (symbol==null){return null;}
        Stock temp = stockRepository.findStockBySymbol(symbol);
        if(temp != null){
            return stockRepository.save(temp);
        }else {
            return stockRepository.save(stock);
        }
    }

    public Stock createStockForWatchlist(int wid, Stock stock){
        Watchlist wl = watchlistRepository.findWatchlistById(wid);
        if(wl == null){
            return null;
        }else{
            String symbol = stock.getSymbol();
            if(symbol==null){return null;}
            Stock temp = stockRepository.findStockBySymbol(symbol);
            if (temp==null){
                Stock s = stockRepository.save(stock);
                List<Stock> stocklist = wl.getStocks();
                stocklist.add(s);
                wl.setStocks(stocklist);
                watchlistRepository.save(wl);
                List<Watchlist> watchlist = s.getWatchlists();
                watchlist.add(wl);
                s.setWatchlists(watchlist);
                return stockRepository.save(s);
            }
            else{
                List<Stock> stocklist = wl.getStocks();
                for (Stock i : stocklist){
                    if (temp.equals(i)){
                        return null;
                    }
                }
                stocklist.add(temp);
                wl.setStocks(stocklist);
                watchlistRepository.save(wl);
                List<Watchlist> watchlist = temp.getWatchlists();
                watchlist.add(wl);
                temp.setWatchlists(watchlist);
                return stockRepository.save(temp);
            }
        }
    }
    public int deleteStockForWatchlist(int wid, int sid){
        Watchlist wl = watchlistRepository.findWatchlistById(wid);
        Stock st = stockRepository.findStockById(sid);
        if(wl == null){
            return 0;
        }
        if(st == null){
            return 0;
        }
        List<Stock> wllist = wl.getStocks();
        List<Watchlist> stlist = st.getWatchlists();
        if (wllist.remove(st) && stlist.remove(wl)){
            wl.setStocks(wllist);
            st.setWatchlists(stlist);
            watchlistRepository.save(wl);
            stockRepository.save(st);
            return 1;
        }
        else{
            return 0;
        }
    }
}
