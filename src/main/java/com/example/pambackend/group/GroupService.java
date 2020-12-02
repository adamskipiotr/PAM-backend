package com.example.pambackend.group;


import lombok.Data;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Service
@Data
public class GroupService {

    private final EntityManager entityManager;
    private final GroupRepository groupRepository;

    public List<StudentsGroupDTO> getAllGroups() {
        List<StudentsGroup> studentsGroups = groupRepository.findAll();
        List<StudentsGroupDTO> returnList = new LinkedList<>();
        for(StudentsGroup studentsGroup: studentsGroups){
            returnList.add(new StudentsGroupDTO(studentsGroup.getGroupID(),studentsGroup.getGroupName()));
        }
        return returnList;
    }

    public void addNewGroup(String newStudentGroupName) {
        StudentsGroup newGroup = new StudentsGroup();
        newGroup.setGroupName(newStudentGroupName);
        groupRepository.save(newGroup);
    }

    @Transactional
    public void addStudentToGroup(Long idGroupToJoin, Long idStudent) {
        String query = "INSERT INTO students_group_students_in_group(assigned_students_groups_groupid, students_in_group_studentid) VALUES (" + idGroupToJoin + "," + idStudent + ")";
        entityManager.createNativeQuery(query).executeUpdate();

    }
}
