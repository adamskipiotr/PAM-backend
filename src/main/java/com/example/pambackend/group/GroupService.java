package com.example.pambackend.group;


import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class GroupService {

    private final GroupRepository groupRepository;
}
