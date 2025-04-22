package com.example.Braclet.Controlleur;

import com.example.Braclet.Entity.Message;
import com.example.Braclet.Entity.MessageTypes;

import com.example.Braclet.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")  // Changed to "/messages" for clarity
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    // Endpoint to get all messages
    @GetMapping(value = "/get", produces = "application/json")
    public List<Message> getMessages() {
        return messageService.getAllMessages();
    }

    // Send message
    @PostMapping(value = "/send", consumes = "application/json")
    public Message sendMessage(@RequestBody Message message) {
        message.setType(MessageTypes.SENT);
        return messageService.saveMessage(message);
    }

    // Receive message
    @PostMapping(value = "/receive", consumes = "application/json", produces = "application/json")
    public Message receiveMessage(@RequestBody Message message) {
        message.setType(MessageTypes.RECEIVED);
        return messageService.saveMessage(message);
    }
}
