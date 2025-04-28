package com.example.Braclet.Service;

import com.example.Braclet.Entity.Bracelet;
import com.example.Braclet.Repository.BraceletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BraceletService {
    @Autowired
    private BraceletRepository braceletRepository;

    public List<Bracelet> getAllBracelets() {
        return braceletRepository.findAll();
    }

    public Bracelet saveBracelet(Bracelet bracelet) {
        return braceletRepository.save(bracelet);
    }
}
