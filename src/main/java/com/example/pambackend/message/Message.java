package com.example.pambackend.message;


import com.example.pambackend.group.StudentsGroup;
import com.example.pambackend.student.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long messageID;

    private String title;
    private String contents;
    private String author;
    @ManyToMany
    @JsonIgnore
    private List<StudentsGroup> recipientsStudentsGroup = new LinkedList<>();

    @ManyToMany
    @JsonIgnore
    private List<Student> studentsWhoSaw = new LinkedList<>();

    public Message(String contents, String title, String author, StudentsGroup recipientsStudentsGroup) {
        this.contents = contents;
        this.title = title;
        this.author = author;
        this.recipientsStudentsGroup.add(recipientsStudentsGroup);
    }

    public Message(String title, String contents, String author) {
        this.contents = contents;
        this.title = title;
        this.author = author;
    }
}
