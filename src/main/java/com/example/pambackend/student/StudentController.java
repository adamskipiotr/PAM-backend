package com.example.pambackend.student;


import com.example.pambackend.message.Message;
import com.example.pambackend.message.MessageDTO;
import com.example.pambackend.user.PAMUser;
import com.example.pambackend.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/add")
    public void addNewUser(@RequestBody StudentCreateDTO studentCreateDTO){
        studentService.addNewUser(studentCreateDTO);
    }

    @PostMapping("/login")
    public boolean loginUser(@RequestBody Student student){
        return studentService.findUser(student);
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<Student> getAllUsers(){
        return studentService.getAllUsers();
    }

    @PostMapping("/getAllMessages")
    @ResponseBody
    public List<MessageDTO> getAllMessages(@RequestBody StudentDTO student) { return studentService.getAllMessages(student);}
}
