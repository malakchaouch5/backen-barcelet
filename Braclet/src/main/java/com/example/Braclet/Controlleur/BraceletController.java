package com.example.Braclet.Controlleur;

import com.example.Braclet.Entity.Bracelet;
import com.example.Braclet.Service.BraceletService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bracelets")
public class BraceletController {
    protected final BraceletService braceletService;

    public BraceletController(BraceletService braceletService) { this.braceletService = braceletService; }

    @GetMapping
    public List<Bracelet> getAllBracelets() {
        return braceletService.getAllBracelets();
    }

    @PostMapping
    public Bracelet createBracelet(@RequestBody Bracelet bracelet) {
        return braceletService.saveBracelet(bracelet);
    }
}

