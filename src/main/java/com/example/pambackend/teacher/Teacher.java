package com.example.pambackend.teacher;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long teacherID;

    private String username;
    private String password;
}