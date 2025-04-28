package com.example.Braclet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDTO {
    private String text;
    private String vocal;
    private boolean anomalyDetected;
    private String type; // "SENT" or "RECEIVED"
    private boolean isTextMessage;
    private Long senderUserId;
    private Long braceletId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getVocal() {
        return vocal;
    }

    public void setVocal(String vocal) {
        this.vocal = vocal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAnomalyDetected() {
        return anomalyDetected;
    }

    public void setAnomalyDetected(boolean anomalyDetected) {
        this.anomalyDetected = anomalyDetected;
    }

    public boolean isTextMessage() {
        return isTextMessage;
    }

    public void setIsTextMessage(boolean textMessage) {
        isTextMessage = textMessage;
    }

    public Long getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(Long senderUserId) {
        this.senderUserId = senderUserId;
    }

    public Long getBraceletId() {
        return braceletId;
    }

    public void setBraceletId(Long braceletId) {
        this.braceletId = braceletId;
    }
}
