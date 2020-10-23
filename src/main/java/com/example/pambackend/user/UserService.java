package com.example.pambackend.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

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
}
