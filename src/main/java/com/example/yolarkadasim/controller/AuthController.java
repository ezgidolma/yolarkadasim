package com.example.yolarkadasim.controller;

import com.example.yolarkadasim.model.AuthResponse;
import com.example.yolarkadasim.model.User;

import com.example.yolarkadasim.model.UserLoginRequest;
import com.example.yolarkadasim.repository.UserRepository;
import com.example.yolarkadasim.service.JwtTokenProvider;
import lombok.AllArgsConstructor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/kullanicilar")
@AllArgsConstructor

public class AuthController {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

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



    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequest request) {
        try {
            // Kullanıcının e-postasına göre veritabanından kullanıcıyı bul
            Optional<User> userOptional = userRepository.findByEposta(request.getEposta());

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                // Veritabanında kullanıcının şifresi hashlenmiş olduğu için, girilen şifre ile karşılaştırırken
                // passwordEncoder.matches metodu kullanılmalıdır.
                if (passwordEncoder.matches(request.getSifre(), user.getSifre())) {
                    // Kullanıcı doğrulandıysa JWT token oluştur
                    Authentication authentication = authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(request.getEposta(), user.getSifre())
                    );

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    String token = jwtTokenProvider.generateToken(authentication.getName());
                    return ResponseEntity.ok(new AuthResponse(token));
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email/password supplied");
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }
}
