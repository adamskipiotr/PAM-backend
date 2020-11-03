package com.example.pambackend.teacher;


import com.example.pambackend.group.StudentsGroup;
import com.example.pambackend.group.GroupRepository;
import com.example.pambackend.message.Message;
import com.example.pambackend.message.MessageDTO;
import com.example.pambackend.message.MessageRepository;
import com.example.pambackend.student.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final MessageRepository messageRepository;
    private final GroupRepository groupRepository;

    public void addNewUser(Teacher user){
        teacherRepository.save(user);
    }

    public boolean findTeacher(Teacher teacher) {
        Optional<Teacher> foundTeacher = teacherRepository.findByNameAndPassword(teacher.getUsername(),teacher.getPassword());
        return foundTeacher.isPresent();
    }

    public List<Teacher> getAllUsers() {
         return teacherRepository.findAll();
    }

    public void saveMessage(MessageDTO messageDTO) {
        StudentsGroup studentsGroupToInform = groupRepository.findById(messageDTO.getGroupID()).get();
        Message newMessage = new Message(messageDTO.getContents(),messageDTO.getTitle(),messageDTO.getAuthor(), studentsGroupToInform);
        messageRepository.save(newMessage);
    }
}
