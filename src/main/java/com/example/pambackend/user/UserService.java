package com.example.pambackend.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void addNewUser(User user){
        userRepository.save(user);
    }

    public void findUser(User user) {
        userRepository.findUser(user.getUsername(),user.getPassword());
    }
}
