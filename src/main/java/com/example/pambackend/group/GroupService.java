package com.example.pambackend.group;


import lombok.Data;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class GroupService {

    private final EntityManager entityManager;
    private final GroupRepository groupRepository;

    public List<StudentsGroupDTO> getAllGroups() {
        List<StudentsGroup> studentsGroups = groupRepository.findAll();
        return studentsGroups.stream()
                        .map(StudentsGroupDTO::new)
                        .collect(Collectors.toCollection(ArrayList::new));
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
