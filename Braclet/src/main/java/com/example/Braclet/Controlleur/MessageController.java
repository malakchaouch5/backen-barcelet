package com.example.Braclet.Controlleur;

import com.example.Braclet.Entity.Message;
import com.example.Braclet.Entity.MessageTypes;

import com.example.Braclet.Service.MessageService;
import com.example.Braclet.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/mess")  // This handles POST /messages/mess
    public ResponseEntity<MessageDTO> sendMessage(@RequestBody MessageDTO messageDTO) {
        try {
            Message message = messageService.sendMessage(messageDTO);
            MessageDTO responseDTO = mapMessageToDTO(message);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private MessageDTO mapMessageToDTO(Message message) {
        MessageDTO dto = new MessageDTO();
        dto.setText(message.getText());
        dto.setVocal(message.getVocal());
        dto.setAnomalyDetected(message.isAnomalyDetected());
        dto.setType(message.getType().name());
        dto.setIsTextMessage(message.isTextMessage());
        if (message.getUserid() != null) {
            dto.setSenderUserId(message.getUserid().getId());
        }
        if (message.getBraceletid() != null) {
            dto.setBraceletId(message.getBraceletid().getId());
        }
        return dto;
    }


    @GetMapping("/{braceletId}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable Long braceletId) {
        List<Message> messages = messageService.getMessagesByBraceletId(braceletId);
        return ResponseEntity.ok(messages);
    }
}
