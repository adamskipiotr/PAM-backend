package com.example.pambackend.user;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public void addNewUser(PAMUser user){
        userService.addNewUser(user);
    }

    @PostMapping("/login")
    public void loginUser(PAMUser user){
        userService.findUser(user);
    }
}
