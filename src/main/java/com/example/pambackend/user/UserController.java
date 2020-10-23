package com.example.pambackend.user;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public void addNewUser(User user){
        userService.addNewUser(user);
    }

    @PostMapping("/login")
    public void loginUser(User user){
        userService.findUser(user);
    }
}
