package com.example.project.controllers;

import com.example.project.models.Admin;
import com.example.project.models.User;
import com.example.project.repositories.AdminRepository;
import com.example.project.repositories.UserRepository;
import com.example.project.services.AdminService;
import com.example.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class AdminController {

    @Autowired
    AdminService service;
    @Autowired
    AdminRepository repository;

    @GetMapping("/api/admins")
    public List<Admin> findAllUsers() {
        return service.getAllAdmin();
    }

    @PostMapping("/admin/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @PostMapping("/admin/login")
    public Admin login(@RequestBody Admin user,
                       HttpSession session) {

        Admin profile = repository.findAdminByCredentials(user.getUsername(), user.getPassword());
        if (profile != null) {
            session.setAttribute("profile", user);
            return profile;
        } else {
            Admin falseUser = new Admin();
            //falseUser.setUsername("PLEASE LOGIN FIRST");
            return falseUser;
        }


    }

    @GetMapping("/admin/profile")
    public Admin profile(HttpSession session) {
        Admin profile = (Admin) session.getAttribute("profile");
        if (profile !=null)
        {
            Admin returnadmins = (repository.findAdminByCredentials(profile.getUsername(), profile.getPassword()));
            return returnadmins;
        }
        else{
            Admin falseUser = new Admin();
            //falseUser.setUsername("PLEASE LOGIN FIRST");
            return falseUser;
        }
    }
}
