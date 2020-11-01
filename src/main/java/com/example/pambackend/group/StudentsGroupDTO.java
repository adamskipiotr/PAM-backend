package com.example.pambackend.group;

import lombok.Data;
import lombok.NonNull;

@Data
public class StudentsGroupDTO {

    private Long groupID;
    @NonNull
    private String groupName;

    public StudentsGroupDTO(Long groupID,String groupName){
        this.groupID = groupID;
        this.groupName = groupName;
    }
}
