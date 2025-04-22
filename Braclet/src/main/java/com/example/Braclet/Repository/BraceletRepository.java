package com.example.Braclet.Repository;

import com.example.Braclet.Entity.Bracelet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BraceletRepository extends JpaRepository<Bracelet, Long> {
    Bracelet save(Bracelet bracelet);

    List<Bracelet> findAll();
}

