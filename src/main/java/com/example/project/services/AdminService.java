package com.example.project.services;

import com.example.project.models.Admin;
import com.example.project.models.User;
import com.example.project.repositories.AdminRepository;
import com.example.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAllAdmin(){return (List<Admin>) adminRepository.findAllAdmins(); }



}
