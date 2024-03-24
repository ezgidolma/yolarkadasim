package com.example.yolarkadasim.service;

import com.example.yolarkadasim.model.User;
import com.example.yolarkadasim.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer id){
        return userRepository.findById(id);
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(Integer id, User user){
        Optional<User> optionalUser = getUserById(id);

        if (optionalUser.isPresent()){
            User currentUser = optionalUser.get();

            currentUser.setAd(user.getAd());
            currentUser.setSoyad(user.getSoyad());
            currentUser.setEposta(user.getEposta());
            currentUser.setSifre(user.getSifre());
            currentUser.setDogum_tarihi(user.getDogum_tarihi());
            currentUser.setKayit_tarihi(user.getKayit_tarihi());
            currentUser.setProfil_resmi(user.getProfil_resmi());

            return userRepository.save(currentUser);
        }

        return null;
    }

    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }

}
