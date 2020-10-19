package com.example.pambackend;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class FirstController {


    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
