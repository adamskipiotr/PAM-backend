package com.example.pambackend.message;

import lombok.Data;

@Data
public class MessageDTO {

    private String contents;
    private Long groupID;
}
