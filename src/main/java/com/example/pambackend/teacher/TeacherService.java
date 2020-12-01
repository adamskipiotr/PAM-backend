package com.example.pambackend.teacher;


import com.example.pambackend.group.StudentsGroup;
import com.example.pambackend.group.GroupRepository;
import com.example.pambackend.message.Message;
import com.example.pambackend.message.MessageDTO;
import com.example.pambackend.message.MessageRepository;
import com.example.pambackend.response.StudentLoginResponse;
import com.example.pambackend.response.TeacherLoginResponse;
import com.example.pambackend.student.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final MessageRepository messageRepository;
    private final GroupRepository groupRepository;
    private final EntityManager entityManager;

    public void addNewUser(TeacherDTO teacherDTO){
        Teacher newTeacher = new Teacher();
        newTeacher.setUsername(teacherDTO.getUsername());
        newTeacher.setPassword(teacherDTO.getPassword());
        teacherRepository.save(newTeacher);
    }

    public TeacherLoginResponse findTeacher(Teacher teacher) {
        TeacherLoginResponse response = new TeacherLoginResponse();
        Optional<Teacher> foundTeacher = teacherRepository.findByNameAndPassword(teacher.getUsername(), teacher.getPassword());
        if (foundTeacher.isPresent()){
            response.setResult(true);
            response.setActiveTeacher(foundTeacher.get().dto());
        }
        else {
            response.setResult(false);
            response.setActiveTeacher(null);
        }
        return response;
    }

    public List<Teacher> getAllUsers() {
         return teacherRepository.findAll();
    }

    public void saveMessage(MessageDTO messageDTO) {
        StudentsGroup studentsGroupToInform = groupRepository.findById(messageDTO.getGroupID()).get();
        Message newMessage = new Message(messageDTO.getContents(),messageDTO.getTitle(),messageDTO.getAuthor(), studentsGroupToInform);
        messageRepository.save(newMessage);
    }

    public List<MessageDTO> getAllMessages(TeacherDTO teacherDTO) {
        boolean messageSeen = false;
        Teacher studentToHandle = teacherRepository.findByID(teacherDTO.getTeacherID());
        //TODO IMPLEMENT
//        List<MessageDTO> messagesForStudent = new LinkedList<>();
//        for (StudentsGroup assignedStudentsGroup : assignedStudentsGroups) {
//            String query = "SELECT * FROM Message m WHERE m.messageid IN (SELECT x.messages_for_group_messageid FROM message_recipients_students_group x WHERE x.recipients_students_group_groupid = " + assignedStudentsGroup.getGroupID() + ")";
//            List<Message> res = entityManager.createNativeQuery(query, Message.class).getResultList();
//
//            for (Message result : res) {
//                messageSeen = false;
//                String checkQuety = "SELECT FROM message_students_who_saw m WHERE m.messages_seen_messageid = " + result.getMessageID() + " AND m.students_who_saw_studentid = " + studentToHandle.getStudentID();
//                List<Object> checktResult = entityManager.createNativeQuery(checkQuety).getResultList();
//                if (checktResult.size() > 0) {
//                    messageSeen = true;
//                }
//                messagesForStudent.add(new MessageDTO(result.getTitle(), result.getContents(), messageSeen,result.getAuthor()));
//            }
//        }
        return null;
    }
}
