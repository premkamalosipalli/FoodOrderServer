package com.booking.food_Order.controller;

import com.booking.food_Order.entity.Login;
import com.booking.food_Order.entity.User;
import com.booking.food_Order.service.UserService;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController @CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user){
        User savedUser = userService.save(user);
        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);   
    }

    @PostMapping("/validateUser")
    public String validateUser(@Valid @RequestBody Login login){
        String validateUser = userService.validateUser(login);
        return validateUser; 
    }

    @GetMapping("/getUser/{userName}")
    public ResponseEntity<User> getUserByUserName(@PathVariable(value = "userName") String userName){
        User user = userService.getUserByUserName(userName);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/countByRole/{role}")
    public int countByRole(@PathVariable(value = "role") String role){
        return (int) userService.countByRole(role);
    }

    @GetMapping("/users/{role}")
    public ResponseEntity<List<User>> findAllByRole(@PathVariable(value = "role") String role){
        List<User> users = userService.findAllByRole(role);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> findByUserId(@PathVariable(value = "userId") long userId){
        User user =  userService.findByUserId(userId);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable(value = "userId") long userId){
        userService.deleteByRestaurantId(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
