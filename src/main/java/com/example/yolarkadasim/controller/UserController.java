package com.example.yolarkadasim.controller;

import com.example.yolarkadasim.model.User;
import com.example.yolarkadasim.repository.UserRepository;
import com.example.yolarkadasim.service.UserService;
import lombok.AllArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kullanicilar")
@AllArgsConstructor
public class UserController {


    private UserService userService;

    private UserRepository userRepository;

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{kullanici_id}")
    public Optional<User> getByIdUser(@PathVariable String kullanici_id){
        return userService.getUserById(kullanici_id);
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/{kullanici_id}")
    public void deleteUser(@PathVariable String kullanici_id){
        userService.deleteUser(kullanici_id);
    }




}
