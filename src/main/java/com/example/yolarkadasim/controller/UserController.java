package com.example.yolarkadasim.controller;

import com.example.yolarkadasim.model.User;
import com.example.yolarkadasim.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kullanıcılar")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{kullanıcıId}")
    public Optional<User> getByIdUser(@PathVariable Integer kullanıcıId){
        return userService.getUserById(kullanıcıId);
    }

    @PostMapping()
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PutMapping("/{kullanıcıId}")
    public User updateUser(@PathVariable Integer kullanıcıId, @RequestBody User user){
        return userService.updateUser(kullanıcıId,user);
    }

    @DeleteMapping("/{kullanıcıId}")
    public void deleteUser(@PathVariable Integer kullanıcıId){
        userService.getUserById(kullanıcıId);
    }
}
