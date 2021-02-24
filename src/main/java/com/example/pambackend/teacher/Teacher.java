package com.example.pambackend.teacher;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long teacherID;

    @NonNull
    private String username;
    @NonNull
    private String password;

    public Teacher(TeacherDTO teacherDTO) {
        username = teacherDTO.getUsername();
        password = teacherDTO.getPassword();
    }

    public TeacherDTO dto(){
        return new TeacherDTO(teacherID,username,password);
    }
}
