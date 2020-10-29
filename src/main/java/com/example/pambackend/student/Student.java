package com.example.pambackend.student;


import com.example.pambackend.group.StudentsGroup;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long studentID;

    private String username;
    private String password;

    @ManyToMany(mappedBy="studentsInGroup")
    @JsonIgnore
    private List<StudentsGroup> assignedStudentsGroups = new LinkedList<>();
}
