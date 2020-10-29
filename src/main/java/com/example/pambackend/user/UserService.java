package com.example.pambackend.user;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void addNewUser(PAMUser user){
        userRepository.save(user);
    }

    public void findUser(PAMUser user) {
        userRepository.findUser(user.getUsername(),user.getPassword());
    }

    public List<PAMUser> getAllUsers() {
         return userRepository.findAll();
    }
}
