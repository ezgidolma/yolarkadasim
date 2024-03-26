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

    @GetMapping("/{kullanici_id}")
    public Optional<User> getByIdUser(@PathVariable Integer kullanici_id){
        return userService.getUserById(kullanici_id);
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PutMapping("/{kullanici_id}")
    public User updateUser(@PathVariable Integer kullanici_id, @RequestBody User user){
        return userService.updateUser(kullanici_id,user);
    }

    @DeleteMapping("/{kullanici_id}")
    public void deleteUser(@PathVariable Integer kullanici_id){
        userService.deleteUser(kullanici_id);
    }


}
