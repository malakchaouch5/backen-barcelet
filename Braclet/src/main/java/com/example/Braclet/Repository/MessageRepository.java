package com.example.Braclet.Repository;

import com.example.Braclet.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByReceiverId(Long receiverId);
    List<Message> findBySenderIdOrReceiverId(Long senderId, Long receiverId);

}
