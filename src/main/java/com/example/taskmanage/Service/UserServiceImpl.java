package com.example.taskmanage.Service;

import com.example.taskmanage.Model.User;
import com.example.taskmanage.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements  UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User signupUser(User user){
        return userRepository.save(user);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
    @Override
    public User findAdminByEmailAndPassword(String email, String password) {
        if (("admin1@gmail.com".equals(email) && "admin1".equals(password)) || ("admin2@gmail.com".equals(email) && "admin2".equals(password)))  {
            User admin = new User();
            admin.setEmail(email);
            return admin;
        } else {
            return null;
        }
    }

    @Override
    public User findById(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(int userId) {
        userRepository.deleteById(userId);
    }

}
