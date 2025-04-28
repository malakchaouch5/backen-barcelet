package com.example.Braclet.Service;

import com.example.Braclet.Repository.VocalRepository;
import com.example.Braclet.Entity.Vocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VocalService {
    @Autowired
    private VocalRepository vocalRepository;

    public List<Vocal> getAllVocals() {
        return vocalRepository.findAll();
    }

    public Vocal saveVocal(Vocal vocal) {
        return vocalRepository.save(vocal);
    }
}
