package com.example.pambackend.teacher;


import lombok.*;

@Data
public class TeacherDTO {

    private Long teacherID;
    private String username;
    private String password;

    public TeacherDTO(Long teacherID, String username, String password) {
        this.teacherID = teacherID;
        this.username = username;
        this.password = password;
    }
}
