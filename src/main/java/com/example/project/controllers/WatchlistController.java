package com.example.project.controllers;

import com.example.project.models.Watchlist;
import com.example.project.services.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class WatchlistController {
    @Autowired
    WatchlistService service;
    @GetMapping("/api/watchlists")
    public List<Watchlist> findAllWatchlists(){
        return service.findAllWatchlists();
    }
    @GetMapping("/api/watchlists/{wid}")
    public Watchlist findWatchlistById(@PathVariable("wid") Integer wid){
        return service.findWatchlistById(wid);
    }
    @GetMapping("/api/users/{uid}/watchlists")
    public List<Watchlist> findWatchlistsForUser(@PathVariable("uid") String uid){
        int uuid;
        try{
            uuid = Integer.parseInt(uid);
            return service.findWatchlistsForUser(uuid);
        }catch (NumberFormatException e){
            List<Watchlist> result = new ArrayList<>();
            return result;
        }
    }
    @PostMapping("/api/users/{uid}/watchlists")
    public Watchlist createWatchlistForUser(@PathVariable("uid") String uid, @RequestBody Watchlist wl){
        int uuid;
        try{
            uuid = Integer.parseInt(uid);
            return service.createWatchlistForUser(uuid, wl);
        }catch (NumberFormatException e){
            return null;
        }
    }
    @DeleteMapping("/api/watchlists/{wid}")
    public int deleteWatchlist(@PathVariable("wid") Integer wid){
        return service.deleteWatchlist(wid);
    }
    @PutMapping("/api/watchlists/{wid}")
    public int updateWatchlist(@PathVariable("wid") Integer wid, @RequestBody Watchlist wl){
        return service.updateWatchlist(wid,wl);
    }
}
