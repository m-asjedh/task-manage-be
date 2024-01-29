package com.example.taskmanage.Repository;

import com.example.taskmanage.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User,Integer> {

    //Custom query method because this not available in CRUD Repository
    User findByEmailAndPassword(String email, String password);

    Optional<User> findById(int userId);

}
