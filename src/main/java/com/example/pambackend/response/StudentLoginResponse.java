package com.example.pambackend.response;


import com.example.pambackend.student.StudentDTO;
import lombok.Data;

@Data
public class StudentLoginResponse {
    private boolean result;
    private StudentDTO activeStudent;
}
