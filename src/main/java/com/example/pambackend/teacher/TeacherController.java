package com.example.pambackend.teacher;


import com.example.pambackend.message.MessageDTO;
import com.example.pambackend.message.MessageService;
import com.example.pambackend.response.TeacherLoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("teacher")
@AllArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping("/add")
    public ResponseEntity<String> addNewUser(@Valid @RequestBody TeacherDTO teacherDTO){
        teacherService.addNewUser(teacherDTO);
        return ResponseEntity.ok("Teacher added to database");
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
    public ResponseEntity<String> sendMessage(@RequestBody MessageDTO message) {
        teacherService.saveMessage(message);
        return ResponseEntity.ok("Message saved in database");}

    @PostMapping("/getAllMessages")
    public List<MessageDTO> getAllMessages(@Valid @RequestBody TeacherDTO teacherDTO) { return teacherService.getAllMessages(teacherDTO);}
}
