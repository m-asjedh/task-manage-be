package com.example.taskmanage.Controller;

import com.example.taskmanage.Model.User;
import com.example.taskmanage.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/taskManager")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    //User is created and entered to the database
    @PostMapping("/signup")
    public String signupUser(@RequestBody User user){
        userService.signupUser(user);
        return  "New user is added to the database";
    }



    //User login by using email and password
    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody User user) {
        String email = user.getEmail();
        String password = user.getPassword();

        User foundUser = userService.findByEmailAndPassword(email, password);

        if (foundUser != null) {
            return ResponseEntity.status(HttpStatus.OK).body(foundUser.getId());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }



    //Admin login
    @PostMapping("/adminLogin")
    public ResponseEntity<Object> loginAdmin(@RequestBody User user) {
        String email = user.getEmail();
        String password = user.getPassword();

        User foundUser = userService.findAdminByEmailAndPassword(email, password);

        if (foundUser != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Admin has logged in");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }




    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.findAll();
    }


    //Admin delete a user and the user's task/tasks
    @DeleteMapping("/deleteUsers/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable int userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
    }

}
