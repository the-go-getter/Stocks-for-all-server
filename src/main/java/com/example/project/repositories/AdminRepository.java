package com.example.project.repositories;

import com.example.project.models.Admin;
import com.example.project.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminRepository extends CrudRepository<Admin, Integer> {


    @Query("SELECT u FROM Admin u")
    public List<Admin> findAllAdmins();

    @Query("SELECT admin FROM Admin admin WHERE admin.username=:username AND admin.password=:password")
    public Admin findAdminByCredentials(
            @Param("username") String username,
            @Param("password") String password
    );

}
