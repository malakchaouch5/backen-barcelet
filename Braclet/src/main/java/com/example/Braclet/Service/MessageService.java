package com.example.Braclet.Service;


import com.example.Braclet.Controlleur.MyAppUserRepository;
import com.example.Braclet.Entity.Bracelet;
import com.example.Braclet.Entity.Message;
import com.example.Braclet.Entity.MessageTypes;
import com.example.Braclet.Entity.MyAppUser;
import com.example.Braclet.Repository.BraceletRepository;
import com.example.Braclet.Repository.MessageRepository;
import com.example.Braclet.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MyAppUserRepository userRepository;

    @Autowired
    private BraceletRepository braceletRepository;

    public Message sendMessage(MessageDTO messageDTO) {
        Message message = new Message();
        message.setText(messageDTO.getText());
        message.setVocal(messageDTO.getVocal());
        message.setAnomalyDetected(messageDTO.isAnomalyDetected());
        message.setIsTextMessage(messageDTO.isTextMessage());
        message.setType(MessageTypes.valueOf(messageDTO.getType())); // Be careful about correct value

        MyAppUser user = userRepository.findById(messageDTO.getSenderUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Bracelet bracelet = braceletRepository.findById(messageDTO.getBraceletId())
                .orElseThrow(() -> new RuntimeException("Bracelet not found"));

        message.setUserid(user);
        message.setBraceletid(bracelet);

        return messageRepository.save(message);
    }

    public List<Message> getMessagesByBraceletId(Long braceletId) {
        return messageRepository.findByBraceletid_Id(braceletId);
    }

}
