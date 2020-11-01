package com.example.pambackend.group;


import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@Data
public class GroupService {

    private final GroupRepository groupRepository;

    public List<StudentsGroupDTO> getAllGroups() {
        List<StudentsGroup> studentsGroups = groupRepository.findAll();
        List<StudentsGroupDTO> returnList = new LinkedList<>();
        for(StudentsGroup studentsGroup: studentsGroups){
            returnList.add(new StudentsGroupDTO(studentsGroup.getGroupID(),studentsGroup.getGroupName()));
        }
        return returnList;
    }
}
