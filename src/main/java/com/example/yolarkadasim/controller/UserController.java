package com.example.yolarkadasim.controller;

import com.example.yolarkadasim.config.JwtUtil;
import com.example.yolarkadasim.model.User;
import com.example.yolarkadasim.model.UserLoginRequest;
import com.example.yolarkadasim.model.request.UserPasswordUpdateRequest;
import com.example.yolarkadasim.repository.UserRepository;
import com.example.yolarkadasim.service.UserService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kullanicilar")
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

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
        try {
            // Kullanıcıyı veritabanında bul
            Optional<User> userOptional = userRepository.findByEposta(request.getEposta());

            // Kullanıcı yoksa veya şifre boşsa hemen hata döndür
            if (userOptional.isEmpty() || request.getSifre() == null || request.getSifre().isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email/password supplied");
            }

            User user = userOptional.get();

            // Veritabanındaki hashlenmiş şifre ile kullanıcının girdiği şifreyi karşılaştır
            if (passwordEncoder.matches(request.getSifre(), user.getSifre())) {
                String token = JwtUtil.generateToken(user);
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email/password supplied");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
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
    public ResponseEntity<String> updateUserPassword(@PathVariable String kullaniciId, @RequestBody UserPasswordUpdateRequest updateRequest) {
        try {
            if (updateRequest == null || updateRequest.getNewPassword()==null) {
                return ResponseEntity.badRequest().body("Şifre boş olamaz.");
            }

            Optional<User> userOptional = userRepository.findById(kullaniciId);
            if (userOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            User kullanici = userOptional.get();

            String hashedPassword = passwordEncoder.encode(updateRequest.getNewPassword());
            kullanici.setSifre(hashedPassword);
            userRepository.save(kullanici);

            return ResponseEntity.ok("Şifre başarıyla güncellendi.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Şifre güncellenirken bir hata oluştu: " + e.getMessage());
        }
    }



}

