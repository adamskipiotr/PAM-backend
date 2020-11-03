package com.example.pambackend.message;

import lombok.Data;

@Data
public class MessageDTO {

    private String title;
    private String contents;
    private String author;
    private Long groupID;

    public MessageDTO(String title, String contents, String author) {
        this.title = title;
        this.contents = contents;
        this.author = author;
    }
}
