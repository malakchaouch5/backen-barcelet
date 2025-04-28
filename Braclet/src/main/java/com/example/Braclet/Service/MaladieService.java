package com.example.Braclet.Service;

import com.example.Braclet.Repository.MaladieRepository;
import com.example.Braclet.Entity.Maladie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaladieService {
    @Autowired
    private MaladieRepository maladieRepository;

    public List<Maladie> getAllMaladies() {
        return maladieRepository.findAll();
    }

    public Maladie saveMaladie(Maladie maladie) {
        return maladieRepository.save(maladie);
    }
}
