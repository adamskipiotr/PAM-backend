package com.example.pambackend.message;


import com.example.pambackend.group.StudentsGroup;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long messageID;

    private String contents;

    @ManyToMany
    @JsonIgnore
    private List<StudentsGroup> recipientsStudentsGroup = new LinkedList<>();

    public Message(String contents, StudentsGroup recipientsStudentsGroup){
        this.contents = contents;
        this.recipientsStudentsGroup.add(recipientsStudentsGroup);
    }

    public Message(String contents){
        this.contents = contents;
    }
}
