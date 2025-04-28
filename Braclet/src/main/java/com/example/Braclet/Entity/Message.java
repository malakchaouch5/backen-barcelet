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
        private MyAppUser userid;

        @ManyToOne
        @JoinColumn(name = "bracelet_id", nullable = false)
        private Bracelet braceletid;


        // Manually add the getter method if Lombok is not working
        public MyAppUser getUserid() {
                return userid;
        }

        public void setUserid(MyAppUser user) {
                this.userid = user;
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
        public void setText(String text) {
                this.text = text;
        }

        public void setIsTextMessage(boolean isTextMessage) {
                this.isTextMessage = isTextMessage;
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


        public void setType(MessageTypes type) {
                this.type = type;
        }

        public Bracelet getBraceletid() {
                return braceletid;
        }

        public void setBraceletid(Bracelet bracelet) {
                this.braceletid = bracelet;
        }
}
