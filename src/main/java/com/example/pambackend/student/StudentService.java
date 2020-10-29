package com.example.pambackend.student;


import com.example.pambackend.group.StudentsGroup;
import com.example.pambackend.message.Message;
import com.example.pambackend.message.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final EntityManager entityManager;
    private final StudentRepository studentRepository;

    public void addNewUser(Student student) {
        studentRepository.save(student);
    }

    public List<Student> getAllUsers() {
        return studentRepository.findAll();
    }

    public List<Message> getAllMessages(StudentDTO studentDTO) {
        Student studentToHandle = studentRepository.findByID(studentDTO.getStudentID());
        List<StudentsGroup> assignedStudentsGroups = studentToHandle.getAssignedStudentsGroups();
        List<Message> messagesForStudent = new LinkedList<>();
        for (StudentsGroup assignedStudentsGroup : assignedStudentsGroups) {
            String query = "SELECT m.contents FROM Message m WHERE m.messageid = (SELECT x.messages_for_group_messageid FROM message_recipients_students_group x WHERE x.messages_for_group_messageid = " + assignedStudentsGroup.getGroupID() + ")";
            List<Object> res = entityManager.createNativeQuery(query).getResultList();
            for (Object result : res) {
                messagesForStudent.add(new Message(result.toString()));
            }
        }
        return messagesForStudent;
    }

    public void findUser(Student student) {
        // TODO Implement
    }
}
