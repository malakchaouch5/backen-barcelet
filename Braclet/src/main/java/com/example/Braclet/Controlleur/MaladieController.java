package com.example.Braclet.Controlleur;

import com.example.Braclet.Entity.Maladie;
import com.example.Braclet.Service.MaladieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maladies")
public class MaladieController {
    @Autowired
    private MaladieService maladieService;

    @GetMapping
    public List<Maladie> getAllMaladies() {
        return maladieService.getAllMaladies();
    }

    @PostMapping
    public Maladie createMaladie(@RequestBody Maladie maladie) {
        return maladieService.saveMaladie(maladie);
    }
}

