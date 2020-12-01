package com.example.pambackend.group;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("group")
@AllArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/getAll")
    @ResponseBody
    public List<StudentsGroupDTO> getAllUsers(){
        return groupService.getAllGroups();
    }

    @PostMapping("/addNewGroup")
    public void addNewGroup(@RequestBody String newStudentGroupName){
        groupService.addNewGroup(newStudentGroupName);
    }
}

