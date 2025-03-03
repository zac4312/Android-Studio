package com.example.demo;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author stude
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
     @GetMapping("/form")
    public String Display(){
    return "form";
    }
    
@PostMapping("/save")
public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
}
    
    @GetMapping
    public List<User> listUser(){
        return userService.listUsers();
    }
    
    @GetMapping("/{Id}")
    public Optional<User> IdUser(@PathVariable Long Id){
        return userService.IdUsers(Id);
    }
    
    @DeleteMapping("/{Id}")
    public String deleteUser(@PathVariable Long Id) {
         userService.deleteUser(Id);
         return "User Deleted";
    }
}
