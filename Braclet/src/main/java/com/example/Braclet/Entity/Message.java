package com.example.Braclet.Entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "message")  // Optional: specify table name if necessary
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String text;
        private String vocal;
        private boolean anomalyDetected;
        @Enumerated(EnumType.STRING)
        private MessageTypes type;
        private boolean isTextMessage;



        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        private MyAppUser user;

        @ManyToOne
        @JoinColumn(name = "bracelet_id", nullable = false)
        private Bracelet bracelet;


        // Manually add the getter method if Lombok is not working
        public MyAppUser getUser() {
                return user;
        }

        public void setUser(MyAppUser user) {
                this.user = user;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getText() {
                return text;
        }

        public void setTextMessage(String textMessage) {
                this.text = textMessage;
        }

        public String getVocal() {
                return vocal;
        }

        public void setVocal(String vocal) {
                this.vocal = vocal;
        }

        public boolean isAnomalyDetected() {
                return anomalyDetected;
        }

        public void setAnomalyDetected(boolean anomalyDetected) {
                this.anomalyDetected = anomalyDetected;
        }

        public MessageTypes getType() {
                return type;
        }

        public boolean isTextMessage() {
                return isTextMessage;
        }

        public void setTextMessage(boolean text) {
                isTextMessage = text;
        }

        public void setType(MessageTypes type) {
                this.type = type;
        }

        public Bracelet getBracelet() {
                return bracelet;
        }

        public void setBracelet(Bracelet bracelet) {
                this.bracelet = bracelet;
        }
}
