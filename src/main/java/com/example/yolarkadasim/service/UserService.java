package com.example.yolarkadasim.service;

import com.example.yolarkadasim.model.User;
import com.example.yolarkadasim.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
   private PasswordEncoder passwordEncoder;


    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> getUserById(String id){
        return userRepository.findById(id);
    }


    public User updateUser(User user){
        String eposta = user.getEposta();

        if (eposta != null) {
            Optional<User> optionalUser = userRepository.findByEposta(eposta);

            if (optionalUser.isPresent()) {
                User currentUser = optionalUser.get();

                currentUser.setAd(user.getAd());
                currentUser.setSoyad(user.getSoyad());
                currentUser.setDogum_tarihi(user.getDogum_tarihi());
                currentUser.setKayit_tarihi(user.getKayit_tarihi());
                currentUser.setProfil_resmi(user.getProfil_resmi());

                if (user.getSifre() != null && !user.getSifre().isEmpty() && !user.getSifre().equals(currentUser.getSifre())) {
                    String hashedPassword = passwordEncoder.encode(user.getSifre());
                    currentUser.setSifre(hashedPassword);
                }
                return userRepository.save(currentUser);
            } else {
                return null;
            }
        }

        return null;

    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }


}
