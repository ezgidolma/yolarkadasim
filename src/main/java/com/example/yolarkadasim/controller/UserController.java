package com.example.yolarkadasim.controller;

import com.example.yolarkadasim.model.User;
import com.example.yolarkadasim.repository.UserRepository;
import com.example.yolarkadasim.service.UserService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final PasswordEncoder passwordEncoder;

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

    @PutMapping
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/{kullanici_id}")
    public void deleteUser(@PathVariable Integer kullanici_id){
        userService.deleteUser(kullanici_id);
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User user){
        try {
            if (userRepository.findByEposta(user.getEposta()).isPresent())
                return ResponseEntity.status(HttpStatus.CONFLICT).body("E-posta already taken. Please try again");
            user.setSifre(passwordEncoder.encode(user.getSifre()));
            User complete = userRepository.save(user);
            return ResponseEntity.ok(HttpStatus.CREATED);
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


}
