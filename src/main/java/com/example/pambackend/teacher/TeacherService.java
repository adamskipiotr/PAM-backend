package com.example.pambackend.teacher;


import com.example.pambackend.group.GroupRepository;
import com.example.pambackend.message.Message;
import com.example.pambackend.message.MessageDTO;
import com.example.pambackend.message.MessageRepository;
import com.example.pambackend.response.TeacherLoginResponse;
import com.example.pambackend.student.Student;
import com.example.pambackend.student.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final MessageRepository messageRepository;
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final EntityManager entityManager;

    public void addNewUser(TeacherDTO teacherDTO) {
        Teacher newTeacher = new Teacher(teacherDTO);

        teacherRepository.save(newTeacher);
    }

    public TeacherLoginResponse findTeacher(Teacher teacher) {
        TeacherLoginResponse response = new TeacherLoginResponse();
        Optional<Teacher> optionalTeacher = teacherRepository.findByNameAndPassword(teacher.getUsername(), teacher.getPassword());
        if(optionalTeacher.isPresent()) {
            Teacher foundTeacher = optionalTeacher.get();
            response.setResult(true);
            response.setActiveTeacher(foundTeacher.dto());
        }
        else {
            response.setResult(false);
            response.setActiveTeacher(new TeacherDTO());
        }
        return response;
    }

    public List<Teacher> getAllUsers() {
        return teacherRepository.findAll();
    }

    public void saveMessage(MessageDTO messageDTO) {
        groupRepository.findById(messageDTO.getGroupID()).ifPresent(studentsGroupToInform -> {
            Message newMessage = new Message(messageDTO,studentsGroupToInform);
            messageRepository.save(newMessage);
        });
    }

    public List<MessageDTO> getAllMessages(TeacherDTO teacherDTO) {
        Teacher teacherToHandle = teacherRepository.findByID(teacherDTO.getTeacherID());
        List<MessageDTO> messageDTOList = new LinkedList<>();
        List<Message> teacherMessages = messageRepository.findAllByAuthor(teacherToHandle.getUsername());
        for (Message message : teacherMessages) {
            String checkQuery = "SELECT m.students_who_saw_studentid FROM message_students_who_saw m WHERE m.messages_seen_messageid = " + message.getMessageID();
            List<?> checkResult = entityManager.createNativeQuery(checkQuery).getResultList();
            List<String> recipients = new LinkedList<>();
            for (Object id : checkResult) {
                Student singleRecipient = studentRepository.findByID(Long.valueOf(id.toString()));
                recipients.add(singleRecipient.getUsername() + ",");
            }
            messageDTOList.add(new MessageDTO(message.getTitle(), recipients.toString(), true, teacherToHandle.getUsername()));
        }
        return messageDTOList;
    }
}
