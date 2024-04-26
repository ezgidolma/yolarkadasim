package com.example.yolarkadasim.controller;

import com.example.yolarkadasim.model.User;
import com.example.yolarkadasim.model.UserLoginRequest;
import com.example.yolarkadasim.repository.UserRepository;
import com.example.yolarkadasim.service.UserService;
import lombok.AllArgsConstructor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/kullanicilar")
@AllArgsConstructor
@CrossOrigin
public class UserController {


    private UserService userService;

    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
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

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginRequest request) {
        // Kullanıcının e-postasına göre veritabanından kullanıcıyı bul
        Optional<User> userOptional = userRepository.findByEposta(request.getEposta());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Kullanıcının girdiği şifreyi ve veritabanından gelen hashlenmiş şifreyi karşılaştır
            if (passwordEncoder.matches(request.getSifre(), user.getSifre())) {
                return ResponseEntity.ok("Login successful!");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email/password supplied");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        try {
            if (userRepository.findByEposta(user.getEposta()).isPresent())
                return ResponseEntity.status(HttpStatus.CONFLICT).body("E-posta already taken. Please try again");
            user.setSifre(passwordEncoder.encode(user.getSifre()));
            userRepository.save(user);
            return ResponseEntity.ok(HttpStatus.CREATED);
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


}
