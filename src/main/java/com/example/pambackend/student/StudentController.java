package com.example.pambackend.student;


import com.example.pambackend.message.MessageDTO;
import com.example.pambackend.response.StudentLoginResponse;
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
    public StudentLoginResponse loginUser(@RequestBody Student student){
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

    @PostMapping("/markAsSeen/{id}")
    public void markMessageAsSeen(@PathVariable("id")Long id,@RequestBody MessageDTO messageDTO) { studentService.markMessageAsSeen(id,messageDTO);}

    @PostMapping("/getUnreadMessagesCounter")
    public Integer getUnreadMessagesCounter(@RequestBody StudentDTO studentDTO) {return  studentService.getUnreadMessagesCounter(studentDTO);}

}
