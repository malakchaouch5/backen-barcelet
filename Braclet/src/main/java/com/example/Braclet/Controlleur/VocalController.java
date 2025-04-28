package com.example.Braclet.Controlleur;

import com.example.Braclet.Entity.Vocal;
import com.example.Braclet.Service.VocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vocals")
public class VocalController {
    @Autowired
    private VocalService vocalService;

    @GetMapping
    public List<Vocal> getAllVocals() {
        return vocalService.getAllVocals();
    }

    @PostMapping
    public Vocal createVocal(@RequestBody Vocal vocal) {
        return vocalService.saveVocal(vocal);
    }
}

