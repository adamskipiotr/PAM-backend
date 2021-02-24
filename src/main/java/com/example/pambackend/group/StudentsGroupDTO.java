package com.example.pambackend.group;

import lombok.Data;
import lombok.NonNull;

@Data
public class StudentsGroupDTO {

    private Long groupID;
    @NonNull
    private String groupName;

    public StudentsGroupDTO(StudentsGroup studentGroup) {
        this.groupID = studentGroup.getGroupID();
        this.groupName = studentGroup.getGroupName();
    }
}
