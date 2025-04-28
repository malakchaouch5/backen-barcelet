package com.example.Braclet.Repository;

import com.example.Braclet.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByBraceletid_Id(Long braceletId);

}
