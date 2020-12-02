package com.example.pambackend.teacher;


import com.example.pambackend.message.MessageDTO;
import com.example.pambackend.message.MessageService;
import com.example.pambackend.response.TeacherLoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("teacher")
@AllArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    private final MessageService messageService;

    @PostMapping("/add")
    public void addNewUser(@RequestBody TeacherDTO teacherDTO){
        teacherService.addNewUser(teacherDTO);
    }

    @PostMapping("/login")
    public TeacherLoginResponse loginTeacher(@RequestBody Teacher user){
       return teacherService.findTeacher(user);
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<Teacher> getAllUsers(){
        return teacherService.getAllUsers();
    }

    @PostMapping("/sendMessage")
    public void sendMessage(@RequestBody MessageDTO message) { teacherService.saveMessage(message);}

    @PostMapping("/getAllMessages")
    public List<MessageDTO> getAllMessages(@RequestBody TeacherDTO teacherDTO) { return teacherService.getAllMessages(teacherDTO);}

    @PostMapping("/getMessageDetails")
    public void getMessageDetails(@RequestBody MessageDTO messageDTO) { messageService.getMessageDetails(messageDTO);}
}
