package com.example.project.services;

import com.example.project.models.Stock;
import com.example.project.models.User;
import com.example.project.models.Watchlist;
import com.example.project.repositories.UserRepository;
import com.example.project.repositories.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WatchlistService {
    @Autowired
    private WatchlistRepository watchlistRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Watchlist> findAllWatchlists(){
        return watchlistRepository.findAllWatchlists();
    }

    public Watchlist findWatchlistById(Integer wid){
        return watchlistRepository.findWatchlistById(wid);
    }

    public List<Watchlist> findWatchlistsForUser(Integer uid){
        User tempuser = userRepository.findUserById(uid);
        if(tempuser==null){
            return null;
        }else{
            return tempuser.getWatchlists();
        }
    }
    public Watchlist createWatchlistForUser(Integer uid, Watchlist wl){
        User user = userRepository.findUserById(uid);
        if (user==null){return null;}
        wl.setUser(user);
        return watchlistRepository.save(wl);
    }
    public int deleteWatchlist(Integer wid){
        Watchlist wl = watchlistRepository.findWatchlistById(wid);
        if(wl == null){
            return 0;
        }
        for (Stock s : wl.getStocks()){
            List<Watchlist> temp = s.getWatchlists();
            temp.remove(wl);
            s.setWatchlists(temp);
        }
        watchlistRepository.deleteById(wid);
        return 1;
    }
    public int updateWatchlist(Integer wid, Watchlist wl){
        Watchlist w = watchlistRepository.findWatchlistById(wid);
        if (w==null){return 0;}
        w.setDescription(wl.getDescription());
        w.setTitle(wl.getTitle());
        watchlistRepository.save(w);
        return 1;
    }
}
