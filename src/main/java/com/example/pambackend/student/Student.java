package com.example.pambackend.student;


import com.example.pambackend.group.StudentsGroup;
import com.example.pambackend.message.Message;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;


@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long studentID;

    @NonNull
    private String username;
    @NonNull
    private String password;

    @ManyToMany(mappedBy="studentsInGroup")
    @JsonIgnore
    private List<StudentsGroup> assignedStudentsGroups = new LinkedList<>();

    @ManyToMany(mappedBy="studentsWhoSaw")
    @JsonIgnore
    private List<Message> messagesSeen = new LinkedList<>();

    public StudentDTO dto(){
        return new StudentDTO(studentID,username,password);
    }
}
