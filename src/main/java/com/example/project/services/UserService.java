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
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WatchlistRepository watchlistRepository;


    public List<User> getAllUsers(){return (List<User>) userRepository.findAll(); }

    public User findUserById(Integer uid) {
        return userRepository.findUserById(uid);
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public int deleteUser(Integer uid){
        User user = userRepository.findUserById(uid);
        if(user==null){return 0;}
        List<Watchlist> templist = user.getWatchlists();
        for(Watchlist wl : templist){
            for (Stock s : wl.getStocks()){
                List<Watchlist> temp = s.getWatchlists();
                temp.remove(wl);
                s.setWatchlists(temp);
            }
            watchlistRepository.deleteById(wl.getId());
        }
        userRepository.deleteById(uid);
        return 1;
    }
    public int updateUser(Integer uid, User user){
        User u = userRepository.findUserById(uid);
        if(u==null){
            return 0;
        }else {
            userRepository.save(user);
            return 1;
        }
    }
}
