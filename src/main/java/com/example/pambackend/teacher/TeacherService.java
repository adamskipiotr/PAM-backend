package com.example.pambackend.teacher;


import com.example.pambackend.group.StudentsGroup;
import com.example.pambackend.group.GroupRepository;
import com.example.pambackend.message.Message;
import com.example.pambackend.message.MessageDTO;
import com.example.pambackend.message.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final MessageRepository messageRepository;
    private final GroupRepository groupRepository;

    public void addNewUser(Teacher user){
        teacherRepository.save(user);
    }

    public void findUser(Teacher user) {
        teacherRepository.findUser(user.getUsername(),user.getPassword());
    }

    public List<Teacher> getAllUsers() {
         return teacherRepository.findAll();
    }

    public void saveMessage(MessageDTO messageDTO) {
        StudentsGroup studentsGroupToInform = groupRepository.findById(messageDTO.getGroupID()).get();
        Message newMessage = new Message(messageDTO.getContents(), studentsGroupToInform);
        messageRepository.save(newMessage);
    }
}
