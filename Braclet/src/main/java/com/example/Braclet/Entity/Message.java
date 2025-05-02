package com.example.Braclet.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        private MyAppUser sender;

        @ManyToOne
        private MyAppUser receiver;

        private String content; // for text

        @Enumerated(EnumType.STRING)
        @Column(name = "type")
        private MessageTypes type;

        private String audioUrl; // for audio messages

        private LocalDateTime timestamp;

        // Getters & Setters

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public MyAppUser getSender() {
                return sender;
        }

        public void setSender(MyAppUser sender) {
                this.sender = sender;
        }

        public MyAppUser getReceiver() {
                return receiver;
        }

        public void setReceiver(MyAppUser receiver) {
                this.receiver = receiver;
        }

        public String getContent() {
                return content;
        }

        public void setContent(String content) {
                this.content = content;
        }

        public MessageTypes getType() {
                return type;
        }

        public void setType(MessageTypes type) {
                this.type = type;
        }

        public String getAudioUrl() {
                return audioUrl;
        }

        public void setAudioUrl(String audioUrl) {
                this.audioUrl = audioUrl;
        }

        public LocalDateTime getTimestamp() {
                return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
                this.timestamp = timestamp;
        }
}
