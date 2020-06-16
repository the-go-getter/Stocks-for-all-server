package com.example.project.controllers;

import com.example.project.models.User;
import com.example.project.repositories.UserRepository;
import com.example.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",allowCredentials = "true",allowedHeaders = "*")
public class UserController {
    @Autowired
    UserService service;
    @Autowired
    UserRepository repository;
    @GetMapping("/api/users")
    public List<User> findAllUsers(){
        return service.getAllUsers();
    }
    @GetMapping("/api/users/{uid}")
    public User findUserById(@PathVariable("uid") Integer uid){
        return service.findUserById(uid);
    }
    @PostMapping("/api/users")
    public User createUser(@RequestBody User user){
        return service.createUser(user);
    }
    @DeleteMapping("/api/users/{uid}")
    public int deleteUser(@PathVariable("uid") Integer uid){
        return service.deleteUser(uid);
    }
    @PutMapping("/api/users/{uid}")
    public int updateUser(@PathVariable("uid") Integer uid, @RequestBody User user){
        return service.updateUser(uid,user);
    }
    @GetMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }
    @PostMapping("/login")
    public User login(@RequestBody User user,
                      HttpSession session) {
        User profile = repository.findUserByCredentials(user.getUsername(), user.getPassword());
        if(profile==null){
            User falseUser =new User();
            falseUser.setUsername("CANNOT FIND");
            return falseUser;
        }
        else {
            session.setAttribute("profile", user);
            return profile;
        }
    }
    @GetMapping("/profile")
    public User profile(HttpSession session) {
        User profile = (User)session.getAttribute("profile");
        if(profile==null) {
            User falseUser =new User();
            falseUser.setUsername("PLEASE LOGIN FIRST");
            return falseUser;
        }

        User returnusers=(repository.findUserByCredentials(profile.getUsername(), profile.getPassword()));
        if(returnusers==null)
        {
            User falseUser =new User();
            falseUser.setUsername("PLEASE LOGIN FIRST");
            return falseUser;
        }
        else {
            return returnusers;
        }
    }


}
