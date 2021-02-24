package com.example.pambackend.student;


import com.example.pambackend.group.StudentsGroup;
import com.example.pambackend.message.Message;
import com.example.pambackend.message.MessageDTO;
import com.example.pambackend.message.MessageRepository;
import com.example.pambackend.response.StudentLoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final EntityManager entityManager;
    private final StudentRepository studentRepository;
    private final MessageRepository messageRepository;

    public void addNewUser(StudentCreateDTO studentCreateDTO) {
        Student student = new Student(studentCreateDTO.getUsername(),studentCreateDTO.getPassword());
        studentRepository.save(student);
    }

    public List<Student> getAllUsers() {
        return studentRepository.findAll();
    }

    public List<MessageDTO> getAllMessages(StudentDTO studentDTO) {
        boolean isMessageSeen;
        Student studentToHandle = studentRepository.findByID(studentDTO.getStudentID());
        List<StudentsGroup> assignedStudentsGroups = studentToHandle.getAssignedStudentsGroups();
        List<MessageDTO> messagesForStudent = new LinkedList<>();
        for (StudentsGroup assignedStudentsGroup : assignedStudentsGroups) {
            String query = "SELECT * FROM Message m WHERE m.messageid IN (SELECT x.messages_for_group_messageid FROM message_recipients_students_group x WHERE x.recipients_students_group_groupid = " + assignedStudentsGroup.getGroupID() + ")";
            List<Message> res = entityManager.createNativeQuery(query, Message.class).getResultList();
            for (Message result : res) {
                isMessageSeen = false;
                //TODO: SPRAWDZIC
                String checkQuery = "SELECT * FROM message_students_who_saw m WHERE m.messages_seen_messageid = " + result.getMessageID() + " AND m.students_who_saw_studentid = " + studentToHandle.getStudentID();
                List<?> checktResult = entityManager.createNativeQuery(checkQuery).getResultList();
                if (checktResult.size() > 0) {
                    isMessageSeen = true;
                }
                messagesForStudent.add(new MessageDTO(result.getTitle(), result.getContents(), isMessageSeen,result.getAuthor()));
            }
        }
        return messagesForStudent;
    }

    public StudentLoginResponse findUser(Student student) {
        StudentLoginResponse response = new StudentLoginResponse();
        Student foundStudent = studentRepository.findByNameAndPassword(student.getUsername(), student.getPassword()).orElse(null);
        response.setResult(foundStudent != null);
        response.setActiveStudent(foundStudent != null ? foundStudent.dto() : null);
        return response;
    }

    @Transactional
    public void markMessageAsSeen(Long id, MessageDTO messageDTO) {
        Student studentToHandle = studentRepository.findByID(id);
        Message messageToHandle = messageRepository.findByTitle(messageDTO.getTitle());
        String checkQuery = "SELECT m.messages_seen_messageid FROM message_students_who_saw m WHERE m.messages_seen_messageid = " + messageToHandle.getMessageID() + " AND m.students_who_saw_studentid = " + studentToHandle.getStudentID();
        List<?> checktResult = entityManager.createNativeQuery(checkQuery).getResultList();
        if (checktResult.size() > 0) {
            return;
        }
        String query = "INSERT INTO message_students_who_saw(messages_seen_messageid, students_who_saw_studentid) VALUES (" + messageToHandle.getMessageID() + "," + studentToHandle.getStudentID() + ")";
        entityManager.createNativeQuery(query).executeUpdate();
    }

    public Integer  getUnreadMessagesCounter(StudentDTO studentDTO) {
        Integer unreadMessagesCounter = 0;
        boolean countThisMessage;
        Student studentRequesting;
        if(studentRepository.findByNameAndPassword(studentDTO.getUsername(), studentDTO.getPassword()).isPresent())
            studentRequesting = studentRepository.findByNameAndPassword(studentDTO.getUsername(), studentDTO.getPassword()).get();
        else {
            return 0;  //TODO Przerobic na throw Exception(?)
        }
        List<StudentsGroup> assignedStudentsGroups = studentRequesting.getAssignedStudentsGroups();

        for (StudentsGroup assignedStudentsGroup : assignedStudentsGroups) {
            String query = "SELECT * FROM Message m WHERE m.messageid IN (SELECT x.messages_for_group_messageid FROM message_recipients_students_group x WHERE x.recipients_students_group_groupid = " + assignedStudentsGroup.getGroupID() + ")";
            List<Message> res = entityManager.createNativeQuery(query, Message.class).getResultList();
            for (Message result : res) {
                countThisMessage = true;
                String queryUnseenMessages = "SELECT m.students_who_saw_studentid FROM message_students_who_saw m WHERE m.messages_seen_messageid = " + result.getMessageID();
                List<BigInteger> studentsWhoSaw = entityManager.createNativeQuery(queryUnseenMessages).getResultList();
                for (BigInteger studentWhoSaw : studentsWhoSaw) {
                    Long studentWhoSawLong = studentWhoSaw.longValue();
                    if (studentWhoSawLong.equals(studentRequesting.getStudentID())) {
                        countThisMessage = false;
                        break;
                    }
                }
                if (countThisMessage) {
                    unreadMessagesCounter++;
                }
            }
        }
        return unreadMessagesCounter;
    }
}
