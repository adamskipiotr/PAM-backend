package com.example.pambackend.message;

import lombok.Data;

@Data
public class MessageDTO {

    private String title;
    private String contents;
    private String author;
    private Boolean seenByUser;
    private Long groupID;

    public MessageDTO(String title, String contents,Boolean seenByUser, String author) {
        this.title = title;
        this.contents = contents;
        this.seenByUser = seenByUser;
        this.author = author;
    }
}
