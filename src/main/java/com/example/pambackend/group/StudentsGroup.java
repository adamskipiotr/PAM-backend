package com.example.pambackend.group;


import com.example.pambackend.message.Message;
import com.example.pambackend.student.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class StudentsGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long groupID;

    private String groupName;

    @ManyToMany
    private List<Student> studentsInGroup = new LinkedList<>();

    @ManyToMany(mappedBy="recipientsStudentsGroup")
    @JsonIgnore
    private List<Message> messagesForGroup = new LinkedList<>();
}
