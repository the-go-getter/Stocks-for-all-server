package com.example.project.repositories;

import com.example.project.models.Watchlist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WatchlistRepository extends CrudRepository<Watchlist, Integer> {
    @Query("SELECT w FROM Watchlist w")
    public List<Watchlist> findAllWatchlists();


    @Query("SELECT w FROM Watchlist w WHERE w.id=:wid")
    public Watchlist findWatchlistById(@Param("wid") Integer wid);

}
