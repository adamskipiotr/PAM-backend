package com.example.pambackend.group;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}

