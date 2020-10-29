package com.example.pambackend.message;


import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class MessageService {

    private final MessageRepository messageRepository;
}
