package com.example.pambackend.teacher;


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
    public void addNewUser(@RequestBody TeacherDTO teacherDTO){
        teacherService.addNewUser(teacherDTO);
    }

    @PostMapping("/login")
    public boolean loginTeacher(@RequestBody Teacher user){
       return teacherService.findTeacher(user);
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<Teacher> getAllUsers(){
        return teacherService.getAllUsers();
    }

    @PostMapping("/sendMessage")
    public void sendMessage(@RequestBody MessageDTO message) { teacherService.saveMessage(message);}
}
