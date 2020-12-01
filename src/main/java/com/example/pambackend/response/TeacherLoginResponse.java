package com.example.pambackend.response;


import com.example.pambackend.teacher.TeacherDTO;
import lombok.Data;

@Data
public class TeacherLoginResponse {
    private boolean result;
    private TeacherDTO activeTeacher;
}
