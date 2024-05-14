package com.example.yolarkadasim.controller;

import com.example.yolarkadasim.config.JwtUtil;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kullanicilar")
@AllArgsConstructor
public class UserController {


    private UserService userService;

    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{kullaniciId}")
    public Optional<User> getByIdUser(@PathVariable String kullaniciId){
        return userService.getUserById(kullaniciId);
    }

    @PostMapping
    public User addUser(@RequestBody User kullanici){
        return userService.addUser(kullanici);
    }

    @PutMapping
    public User updateUser(@RequestBody User kullanici){
        return userService.updateUser(kullanici);
    }

    @DeleteMapping("/{kullaniciId}")
    public void deleteUser(@PathVariable String kullaniciId){
        userService.deleteUser(kullaniciId);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginRequest request) {
        // Kullanıcının e-postasına göre veritabanından kullanıcıyı bul
        Optional<User> userOptional = userRepository.findByEposta(request.getEposta());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Kullanıcının girdiği şifreyi ve veritabanından gelen hashlenmiş şifreyi karşılaştır
            if (passwordEncoder.matches(request.getSifre(), user.getSifre())) {
                String token = JwtUtil.generateToken(user);
                return ResponseEntity.ok(token);
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
            // Kayıt işlemi başarılıysa JWT tokeni oluştur ve döndür
            String token = JwtUtil.generateToken(user);
            return ResponseEntity.ok(token);
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/{kullaniciId}")
    public ResponseEntity<String> updateUserPassword(@PathVariable String kullaniciId, @RequestBody String newPassword) {
        User kullanici = userService.findById(kullaniciId);
        if (kullanici == null) {
            return ResponseEntity.notFound().build();
        }

        // Yeni şifreyi hashle
        String hashedPassword = passwordEncoder.encode(newPassword);

        // Kullanıcının şifresini güncelle
        kullanici.setSifre(hashedPassword);
        userService.save(kullanici);

        return ResponseEntity.ok("Şifre başarıyla güncellendi.");
    }


}
