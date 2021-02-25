package com.example.pambackend.teacher;


import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class TeacherDTO {

    private Long teacherID;
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;

    public TeacherDTO(Long teacherID, String username, String password) {
        this.teacherID = teacherID;
        this.username = username;
        this.password = password;
    }
}
