package com.example.pambackend.user;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public void addNewUser(@RequestBody PAMUser user){
        userService.addNewUser(user);
    }

    @PostMapping("/login")
    public void loginUser(@RequestBody PAMUser user){
        userService.findUser(user);
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<PAMUser> getAllUsers(){
        return userService.getAllUsers();
    }
}
