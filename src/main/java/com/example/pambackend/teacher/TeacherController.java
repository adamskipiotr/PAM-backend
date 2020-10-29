package com.example.pambackend.teacher;


import com.example.pambackend.message.Message;
import com.example.pambackend.message.MessageDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("teacher")
@AllArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping("/add")
    public void addNewUser(@RequestBody Teacher user){
        teacherService.addNewUser(user);
    }

    @PostMapping("/login")
    public void loginUser(@RequestBody Teacher user){
        teacherService.findUser(user);
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<Teacher> getAllUsers(){
        return teacherService.getAllUsers();
    }

    @PostMapping("/sendMessage")
    public void sendMessage(@RequestBody MessageDTO message) { teacherService.saveMessage(message);}
}
