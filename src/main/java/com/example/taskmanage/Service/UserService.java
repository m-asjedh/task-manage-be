package com.example.taskmanage.Service;

import com.example.taskmanage.Model.User;

import java.util.List;

public interface UserService {

    public User signupUser(User user);

    User findByEmailAndPassword(String email,String password);

    User findAdminByEmailAndPassword(String email, String password);

    User findById(int userId);


    void deleteUserById(int userId);

    List<User> findAll();
}
