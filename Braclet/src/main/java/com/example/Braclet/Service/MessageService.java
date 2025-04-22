package com.example.Braclet.Service;


import com.example.Braclet.Controlleur.MyAppUserRepository;
import com.example.Braclet.Entity.Message;
import com.example.Braclet.Entity.MessageTypes;
import com.example.Braclet.Entity.MyAppUser;
import com.example.Braclet.Repository.MessageRepository;
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

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    private final MessageRepository messageRepository;
    private final MyAppUserRepository myAppUserRepository;


    @Autowired
    public MessageService(MessageRepository messageRepository, MyAppUserRepository myAppUserRepository) {
        this.messageRepository = messageRepository;
        this.myAppUserRepository = myAppUserRepository;
    }

    // Method to fetch all messages
    public List<Message> getAllMessages() {
        List<Message> messages = messageRepository.findAll();
        logger.info("Fetched {} messages", messages.size());
        return messages;
    }

    // Method to save a new message
    @Transactional
    public Message saveMessage(Message message) {
        logger.info("Saving message from user {}", message.getUser().getId());

        // Fetch user
        MyAppUser user = myAppUserRepository.findById(message.getUser().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        message.setUser(user);

        // Set default type to SENT if not defined
        if (message.getType() == null) {
            message.setType(MessageTypes.SENT);
        }

        // Auto-convert vocal <-> text simulation
        if (message.isTextMessage()) {
            // Text was the original -> simulate conversion to vocal
            message.setVocal("Converted vocal from: " + message.getText());
        } else {
            // Vocal was the original -> simulate conversion to text
            message.setTextMessage("Converted text from vocal: " + message.getVocal());
        }
        // Example inside saveMessage()
        if (message.getText() != null && message.getText().toLowerCase().contains("help")) {
            message.setAnomalyDetected(true);
        } else {
            message.setAnomalyDetected(false);
        }


        // Save
        Message savedMessage = messageRepository.save(message);
        logger.info("Message saved with ID {}", savedMessage.getId());
        return savedMessage;
    }

}