package com.example.pambackend.student;


import com.example.pambackend.group.StudentsGroup;
import com.example.pambackend.message.Message;
import com.example.pambackend.message.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    private final EntityManager entityManager;
    private final StudentRepository studentRepository;

    public void addNewUser(StudentCreateDTO studentCreateDTO) {
        Student student = new Student();
        student.setUsername(studentCreateDTO.getUsername());
        student.setPassword(studentCreateDTO.getPassword());
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
            String query = "SELECT m.contents FROM Message m WHERE m.messageid IN (SELECT x.messages_for_group_messageid FROM message_recipients_students_group x WHERE x.recipients_students_group_groupid = " + assignedStudentsGroup.getGroupID() + ")";
            List<Object> res = entityManager.createNativeQuery(query).getResultList();
            for (Object result : res) {
                messagesForStudent.add(new Message(result.toString()));
            }
        }
        return messagesForStudent;
    }

    public boolean findUser(Student student) {
        Optional<Student> foundStudent = studentRepository.findByNameAndPassword(student.getUsername(),student.getPassword());
        return foundStudent.isPresent();
    }
}
