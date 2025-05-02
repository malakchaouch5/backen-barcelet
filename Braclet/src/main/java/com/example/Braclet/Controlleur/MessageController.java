package com.example.Braclet.Controlleur;

import com.example.Braclet.Entity.Message;
import com.example.Braclet.Entity.MessageTypes;
import com.example.Braclet.Repository.MessageRepository;
import com.example.Braclet.Controlleur.MyAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*")
public class MessageController {

    @Autowired
    private MessageRepository messageRepo;

    @Autowired
    private MyAppUserRepository userRepo;

    @PostMapping("/text")
    public ResponseEntity<Message> sendText(@RequestBody Message msg) {
        msg.setTimestamp(LocalDateTime.now());
        msg.setType(MessageTypes.TEXT);
        return ResponseEntity.ok(messageRepo.save(msg));
    }

    @PostMapping("/audio")
    public ResponseEntity<Message> uploadAudio(@RequestParam("file") MultipartFile file,
                                               @RequestParam Long senderId,
                                               @RequestParam Long receiverId) throws IOException {

        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path path = Paths.get("audios/" + filename);
        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());

        Message msg = new Message();
        msg.setType(MessageTypes.AUDIO);
        msg.setAudioUrl("/api/messages/audio/" + filename);
        msg.setSender(userRepo.findById(senderId).orElseThrow());
        msg.setReceiver(userRepo.findById(receiverId).orElseThrow());
        msg.setTimestamp(LocalDateTime.now());

        return ResponseEntity.ok(messageRepo.save(msg));
    }

    @GetMapping("/audio/{filename}")
    public ResponseEntity<Resource> getAudio(@PathVariable String filename) throws IOException {
        Path path = Paths.get("audios/" + filename);
        Resource file = new UrlResource(path.toUri());

        if (!file.exists() || !file.isReadable()) {
            throw new RuntimeException("Audio not found: " + filename);
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                .contentType(MediaType.parseMediaType("audio/mpeg"))
                .body(file);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable Long userId) {
        List<Message> messages = messageRepo.findBySenderIdOrReceiverId(userId, userId);
        return ResponseEntity.ok(messages);
    }

}
